// 2.03-iac-shebang
///usr/bin/env jbang "$0" "$@" ; exit $?
// 2.04-iac-dependencies
//DEPS com.pulumi:pulumi:1.+
//DEPS com.ovhcloud.pulumi.ovh:pulumi-ovh:1.6.0
//DEPS org.slf4j:slf4j-api:2.1.0-alpha1
//DEPS org.slf4j:slf4j-log4j12:2.1.0-alpha1

import java.time.Duration;

import com.ovhcloud.pulumi.ovh.CloudProject.Database;
import com.ovhcloud.pulumi.ovh.CloudProject.DatabaseArgs;
import com.ovhcloud.pulumi.ovh.CloudProject.Kube;
import com.ovhcloud.pulumi.ovh.CloudProject.KubeArgs;
import com.ovhcloud.pulumi.ovh.CloudProject.KubeNodePool;
import com.ovhcloud.pulumi.ovh.CloudProject.KubeNodePoolArgs;
import com.ovhcloud.pulumi.ovh.CloudProject.inputs.DatabaseNodeArgs;
import com.ovhcloud.pulumi.ovh.CloudProjectDatabase.DatabaseInstance;
import com.ovhcloud.pulumi.ovh.CloudProjectDatabase.DatabaseInstanceArgs;
import com.ovhcloud.pulumi.ovh.CloudProjectDatabase.PostgresSqlUser;
import com.ovhcloud.pulumi.ovh.CloudProjectDatabase.PostgresSqlUserArgs;
import com.pulumi.Context;
import com.pulumi.Pulumi;
import com.pulumi.resources.CustomResourceOptions;
import com.pulumi.resources.CustomTimeouts;

public class JarvisIaC {

    // 2.05-iac-ovhcloud-serviceId
    private final static String OVH_CLOUD_PROJECT_SERVICE = System.getenv("OVH_CLOUD_PROJECT_SERVICE");

    // 2.06-iac-timeout
    private final static CustomResourceOptions timeout = CustomResourceOptions.builder()
    .customTimeouts(
        CustomTimeouts.builder()
            .create(Duration.ofMinutes(60))
            .build()).build();

    public static void main(String[] args) {
        Pulumi.run(JarvisIaC::stack);
    }

    public static void stack(Context ctx) {
        // 2.14-iac-call-kube
        createK8s(ctx);

        // 2.15-iac-call-db
        createDB(ctx);
    }

    private static void createK8s(Context ctx) {
        // 2.07-iac-kube-details
        KubeArgs kubeDetails = KubeArgs.builder()
                                        .serviceName(OVH_CLOUD_PROJECT_SERVICE)
                                        .name("jarvis-devoxx-01")
                                        .region("GRA7")
                                    .build();
        Kube kube = new Kube("jarvis-devoxx-01", kubeDetails, timeout);
        
        // 2.08-iac-kube-nodepool-details
        KubeNodePoolArgs nodePoolDetails = KubeNodePoolArgs.builder()
                                            .serviceName(OVH_CLOUD_PROJECT_SERVICE)
                                            .name("jarvis-devoxx-01-nodepool")
                                            .flavorName("d2-4")
                                            .kubeId(kube.id().asPlaintext())
                                            .minNodes(1)
                                            .maxNodes(1)
                                        .build();
        KubeNodePool nodePool = new KubeNodePool("jarvis-devoxx-01-nodepool", nodePoolDetails, timeout);

        // 2.09-iac-kube-kubeconfig
        ctx.export("kubeconfig", kube.kubeconfig());
    }

    private static void createDB(Context ctx) {
        // 2.10-iac-db-details
        DatabaseNodeArgs databaseNodeArgs = DatabaseNodeArgs.builder()
            .region("GRA")
            .build();

        DatabaseArgs databaseArgs = DatabaseArgs.builder()
            .description("jarvis-database-01")
            .flavor("db1-4")
            .plan("essential")
            .serviceName(OVH_CLOUD_PROJECT_SERVICE)
            .engine("postgresql")
            .version("16")
            .nodes(databaseNodeArgs)
            .build();

        Database database = new Database("jarvis-database-01", databaseArgs, timeout);

        // 2.11-iac-db-postgres-user
        PostgresSqlUserArgs postgresSqlUserArgs = PostgresSqlUserArgs.builder()
                .serviceName(OVH_CLOUD_PROJECT_SERVICE)
                .name("avnadmin")
                .clusterId(database.id())
                .passwordReset("⚡️")
                .build();

        PostgresSqlUser postgresSqlUser = new PostgresSqlUser("avnadmin", postgresSqlUserArgs, timeout);
        postgresSqlUser.passwordReset();

        // 2.12-iac-db-postgres-instance`
        DatabaseInstanceArgs databaseInstanceArgs = DatabaseInstanceArgs.builder()
            .clusterId(database.id())
            .serviceName(OVH_CLOUD_PROJECT_SERVICE)
            .engine("postgresql")
            .name("jarvis-ai-embeddings-01")
            .build();

        DatabaseInstance databaseInstance = new DatabaseInstance("jarvis-ai-embeddings-01", databaseInstanceArgs, timeout);
        
        // 2.13-iac-db-conf-export
        ctx. export("db_port", database.endpoints().applyValue(endpoint -> endpoint.getFirst().port()));
        ctx. export("db_host", database.endpoints().applyValue(endpoint -> endpoint.getFirst().domain()));
        ctx. export("db_id", database.id());

        ctx.export("avnadmin-password", postgresSqlUser.password());
    }
}
