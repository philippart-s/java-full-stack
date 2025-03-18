// 01-iac-shebang
///usr/bin/env jbang "$0" "$@" ; exit $?
// 02-iac-dependencies
//DEPS com.pulumi:pulumi:1.+
//DEPS com.ovhcloud.pulumi.ovh:pulumi-ovh:1.6.0

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

public class JarvisIaC {

    // 03-iac-ovhcloud-serviceId
    private final static String OVH_CLOUD_PROJECT_SERVICE = System.getenv("OVH_CLOUD_PROJECT_SERVICE");

    public static void main(String[] args) {
        Pulumi.run(JarvisIaC::stack);
    }

    public static void stack(Context ctx) {
        // First create the Kubernetes
        createK8s(ctx);

        // Next create the database
        createDB(ctx);
    }

    private static void createK8s(Context ctx) {
        // 04-iac-kube-details
        KubeArgs kubeDetails = KubeArgs.builder()
                                        .serviceName(OVH_CLOUD_PROJECT_SERVICE)
                                        .region("GRA7")
                                    .build();
        Kube kube = new Kube("jarvis", kubeDetails);

        // 05-iac-kube-nodepool-details
        KubeNodePoolArgs nodePoolDetails = KubeNodePoolArgs.builder()
                                            .serviceName(OVH_CLOUD_PROJECT_SERVICE)
                                            .flavorName("d2-4")
                                            .kubeId(kube.id().asPlaintext())
                                            .minNodes(1)
                                            .maxNodes(1)
                                        .build();
        KubeNodePool nodePool = new KubeNodePool("jarvis-nodepool", nodePoolDetails);

        // 06-iac-kube-kubeconfig
        ctx.export("kubeconfig", kube.kubeconfig());
    }

    private static void createDB(Context ctx) {
        // 06-iac-db-details
        DatabaseNodeArgs databaseNodeArgs = DatabaseNodeArgs.builder()
            .region("GRA")
            .build();

        DatabaseArgs databaseArgs = DatabaseArgs.builder()
            .description("Jarvis embedding")
            .flavor("db1-4")
            .plan("essential")
            .serviceName(OVH_CLOUD_PROJECT_SERVICE)
            .engine("postgresql")
            .version("16")
            .nodes(databaseNodeArgs)
            .build();

        Database database = new Database("Jarvis database", databaseArgs);

        // 07-iac-db-postgres-user
        PostgresSqlUserArgs postgresSqlUserArgs = PostgresSqlUserArgs.builder()
        .serviceName(OVH_CLOUD_PROJECT_SERVICE)
        .clusterId(database.id())
        .build();

        PostgresSqlUser postgresSqlUser = new PostgresSqlUser("avnadmin", postgresSqlUserArgs);
        postgresSqlUser.passwordReset();

        // 08-iac-db-postgres-instance`
        DatabaseInstanceArgs databaseInstanceArgs = DatabaseInstanceArgs.builder()
            .clusterId(database.id())
            .serviceName(OVH_CLOUD_PROJECT_SERVICE)
            .engine("postgresql")
            .name("jarvis-ai-embeddings")
            .build();

        DatabaseInstance databaseInstance = new DatabaseInstance("jarvis ai embedding", databaseInstanceArgs);
        
        // 09-iac-db-conf-export
        ctx. export("cluster_uri", database.endpoints().applyValue(endpoint -> endpoint.getFirst().uri()));
        ctx. export("db_port", database.endpoints().applyValue(endpoint -> endpoint.getFirst().port()));
        ctx. export("db_host", database.endpoints().applyValue(endpoint -> endpoint.getFirst().domain()));

        ctx.export("avnadmin-password", postgresSqlUser.password());
    }
}
