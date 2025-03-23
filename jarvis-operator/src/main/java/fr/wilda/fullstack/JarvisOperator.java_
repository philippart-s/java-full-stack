package fr.wilda.fullstack;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;

@Version("v1")
@Group("fullstack.wilda.fr")
public class JarvisOperator extends CustomResource<JarvisOperatorSpec, JarvisOperatorStatus> implements Namespaced { }