package fr.wilda.fullstack;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.EnvVarSource;
import io.fabric8.kubernetes.api.model.SecretKeySelector;
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
    private static final Logger log = LoggerFactory.getLogger(JarvisOperatorReconciler.class);

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
    private Deployment makeDeployment(String currentRelease, JarvisOperator releaseDetector) {
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
                            .withEnv(List.of(
                        new EnvVar("OVH_CONSUMER_KEY", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_CONSUMER_KEY", "devoxx-secrets", null))),
                        new EnvVar("OVH_APPLICATION_KEY", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_APPLICATION_KEY", "devoxx-secrets", null))),
                        new EnvVar("OVH_CLOUD_PROJECT_SERVICE", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_CLOUD_PROJECT_SERVICE", "devoxx-secrets", null))),
                        new EnvVar("OVH_APPLICATION_SECRET", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_APPLICATION_SECRET", "devoxx-secrets", null))),
                        new EnvVar("OVH_AI_ENDPOINTS_MODEL_URL", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_AI_ENDPOINTS_MODEL_URL", "devoxx-secrets", null))),
                        new EnvVar("OVH_AI_ENDPOINTS_MODEL_NAME", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_AI_ENDPOINTS_MODEL_NAME", "devoxx-secrets", null))),
                        new EnvVar("OVH_AI_ENDPOINTS_ACCESS_TOKEN", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_AI_ENDPOINTS_ACCESS_TOKEN", "devoxx-secrets",
                                                null))),
                        new EnvVar("OVH_DB_USERNAME", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_DB_USERNAME", "devoxx-secrets", null))),
                        new EnvVar("OVH_DB_PASSWORD", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_DB_PASSWORD", "devoxx-secrets", null))),
                        new EnvVar("OVH_DB_HOST", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_DB_HOST", "devoxx-secrets", null))),
                        new EnvVar("OVH_DB_PORT", null,
                                new EnvVarSource(null, null, null,
                                        new SecretKeySelector("OVH_DB_PORT", "devoxx-secrets", null)))))
                .addNewPort()
                .withContainerPort(80)
                .endPort()
                .endContainer()
                .endSpec()
                .endTemplate()
                .endSpec()
                .build();

        // deployment.addOwnerReference(releaseDetector);

        log.info("Generated deployment {}", Serialization.asYaml(deployment));

        return deployment;
    }

    @Override
    public List<EventSource<?, JarvisOperator>> prepareEventSources(EventSourceContext<JarvisOperator> context) {
        simpleInboundEventSource = createSimpleInboundEventSource();

        return List.of(simpleInboundEventSource);
    }

    @Override
    public UpdateControl<JarvisOperator> reconcile(JarvisOperator resource, Context<JarvisOperator> context) {
        return UpdateControl.noUpdate();
    }
}