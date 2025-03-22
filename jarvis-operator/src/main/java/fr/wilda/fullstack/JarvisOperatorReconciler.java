package fr.wilda.fullstack;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.api.model.EnvFromSourceBuilder;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.utils.Serialization;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.EventSourceContext;
import io.javaoperatorsdk.operator.api.reconciler.Reconciler;
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl;
import io.javaoperatorsdk.operator.processing.event.ResourceID;
import io.javaoperatorsdk.operator.processing.event.source.EventSource;
import io.javaoperatorsdk.operator.processing.event.source.inbound.SimpleInboundEventSource;
import jakarta.enterprise.inject.Produces;

public class JarvisOperatorReconciler implements Reconciler<JarvisOperator> {
    private static final Logger _LOG = LoggerFactory.getLogger(JarvisOperatorReconciler.class);

    // 4.12-add-reconciler-var
    // Flag to know if the operator must deploy the application on a new event.
    private String deploy = "❌";

    // ID of the created custom resource.
    private ResourceID resourceID;

    // Current deployed release.
    private String currentRelease;

    // Fabric0 kubernetes client.
    private final KubernetesClient client;

    // To manage external event
    private SimpleInboundEventSource simpleInboundEventSource;

    public JarvisOperatorReconciler(KubernetesClient client) {
        this.client = client;
    }

    // 4.13-add-event-source
    @Produces
    public SimpleInboundEventSource createSimpleInboundEventSource() {
        return new SimpleInboundEventSource();
    }

    // 4.14-create-deployment
    private Deployment makeDeployment(String currentRelease, JarvisOperator jarvisOperator) {
        Deployment deployment = new DeploymentBuilder()
                .withNewMetadata()
                    .withName("jarvis-deployment")
                    .addToLabels("app", "jarvis")
                .endMetadata()
                .withNewSpec()
                    .withReplicas(1)
                    .withNewSelector()
                        .withMatchLabels(Map.of("app", "jarvis"))
                    .endSelector()
                .withNewTemplate()
                    .withNewMetadata()
                        .addToLabels("app", "jarvis")
                    .endMetadata()
                    .withNewSpec()
                        .addNewContainer()
                            .withName("jarvis")
                            .withImage("95y036e0.gra7.container-registry.ovh.net/devoxx/jarvis-app" + ":" + currentRelease)
                            .withEnvFrom(new EnvFromSourceBuilder()
                                                .withNewSecretRef("devoxx-secrets", false)
                                                .build())
                            .addNewPort()
                                .withContainerPort(80)
                            .endPort()
                        .endContainer()
                    .endSpec()
                .endTemplate()
            .endSpec()
        .build();

        //deployment.addOwnerReference(jarvisOperator);

        _LOG.info("Generated deployment {}", Serialization.asYaml(deployment));

        return deployment;
    }

    // 4.15-create-service
    private Service makeService(JarvisOperator jarvisOperator) {
        Service service = new ServiceBuilder()
                .withNewMetadata()
                    .withName("jarvis-service")
                    .addToLabels("app", "jarvis")
                .endMetadata()
                .withNewSpec()
                    .withType("NodePort")
                    .withSelector(Map.of("app", "jarvis"))
                        .addNewPort()
                            .withPort(80)
                            .withTargetPort(new IntOrString(8080))
                            .withNodePort(30080)
                        .endPort()
                .endSpec()
        .build();

        //service.addOwnerReference(jarvisOperator);

        _LOG.info("Generated service {}", Serialization.asYaml(service));

        return service;
    }

    @Override
    public List<EventSource<?, JarvisOperator>> prepareEventSources(EventSourceContext<JarvisOperator> context) {
        simpleInboundEventSource = createSimpleInboundEventSource();

        return List.of(simpleInboundEventSource);
    }

    @Override
    public UpdateControl<JarvisOperator> reconcile(JarvisOperator resource, Context<JarvisOperator> context) {
        _LOG.info("⚡️ Event occurs ! Reconcile called.");

        // 4.16-namespace-and-status
        // Get namespace and status
        String namespace = resource.getMetadata().getNamespace();
        String statusDeployedRelease = (resource.getStatus() != null ? resource.getStatus().getDeployedRelase() : "");

        deploy = resource.getSpec().getDeploy();
        _LOG.info("Deploy Jarvis ? {}", deploy);

        // Get configuration
        resourceID = ResourceID.fromResource(resource);

        // 4.17-deploy-jarvis
        if ("✅".equalsIgnoreCase(deploy) && currentRelease != null && currentRelease.trim().length() != 0
                && !currentRelease.equalsIgnoreCase(statusDeployedRelease)) {
            // 4.18-make-deployment
            // Create deployment application
            _LOG.info("🔀 Deploy the new release {} !", currentRelease);
            Deployment deployment = makeDeployment(currentRelease, resource);
            Deployment existingDeployment = client.apps().deployments().inNamespace(namespace)
                    .withName(deployment.getMetadata().getName()).get();
            if (existingDeployment == null) {
                client.apps().deployments().inNamespace(namespace).resource(deployment).create();
            } else {
                client.apps().deployments().inNamespace(namespace).resource(deployment).update();
            }

            // 4.19-make-service
            // Create service
            Service service = makeService(resource);
            Service existingService = client.services().inNamespace(resource.getMetadata().getNamespace())
                    .withName(service.getMetadata().getName()).get();
            if (existingService == null) {
                client.services().inNamespace(namespace).resource(service).create();
            } else {
                client.services().inNamespace(namespace).resource(service).update();
            }

            // 4.20-update-status
            // Update the status
            if (resource.getStatus() != null) {
                resource.getStatus().setDeployedRelase(currentRelease);
            } else {
                JarvisOperatorStatus jarvisOperatorStatus = new JarvisOperatorStatus();
                jarvisOperatorStatus.setDeployedRelase(currentRelease);
                resource.setStatus(jarvisOperatorStatus);
            }
        }

        // 4.21-apply-status-patch
        return UpdateControl.patchStatus(resource);
    }

    /**
     * Fire an event to awake the reconciler.
     * 
     * @param tag The new tag on GitHub.
     */
    // 4.22-add-fire-event-method
    public void fireEvent(String tag) {
        if (resourceID != null) {
            currentRelease = tag;
            simpleInboundEventSource.propagateEvent(resourceID);
        } else {
            _LOG.info("🚫 No resource created, nothing to do.");
        }
    }
}