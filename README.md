# ☕️ Java full stack conference 🏗️
Code source for the Java full stack conference

# 🧑‍💻 How to use / contribute 🐳

The simpliest way to use this code is to re-open the project with the Dev Containers.

# ⚡️ 01 - Quarkus app : [jarvis-app](./jarvis-app/) ⚡️
## 📼 Backend 📼

 - run [_init/01-app-create.sh](./_init/1.01-app-create.sh)
 - run [1.02-start-quarkus-dev.sh](./jarvis-app/1.02-start-quarkus-dev.sh)
 - run [1.03-test-greetings.sh](./jarvis-app/1.03-test-greetings.sh)
 - connect to the developer console: `http://localhost:8080`
 - add `quarkus-langchain4j-mistral-ai` extension (`1.04-add-quarkus-langchain-ext.sh`)
 - configure extension in [application.properties](./jarvis-app/src/main/resources/application.properties) file (`1.05-app-langchain4j-cfg`)
 - create [ChatbotService](./jarvis-app/src/main/java/fr/wilda/fullstack/services/ChatbotService.java) interface
  - add AI interface annotations (`1.06-app-ai-service`)
  - add AI interface prompts (`1.07-app-ai-prompts`)
 - create [ChatbotRestResource](./jarvis-app/src/main/java/fr/wilda/fullstack/resources/ChatbotRestResource.java) class
  - add path (`1.08-app-chatbot-rest-path`)
  - inject rest client (`1.09-app-chatbot-rest-client`)
  - add `ask` method anotions (`1.10-app-chatbot-rest-ask-annot`)
  - call the model (`1.11-app-chatbot-rest-call-model`)
 - test the chatbot: [1.12-test-chatbot.sh](./jarvis-app/1.12-test-chatbot.sh)
 - add `quarkus-rest-jackson` and `quarkus-rest-client-jackson` extension and remove the `quarkus-rest` extension: [1.13-add-quarkus-rest-ext.sh](./jarvis-app/1.13-add-quarkus-rest-ext.sh)
 - add rest client configuration in [application.properties](./jarvis-app/src/main/resources/application.properties) (`1.14-app-rest-client-cfg`)
 - create [DevoxxCFPService](./jarvis-app/src/main/java/fr/wilda/fullstack/services/DevoxxCFPService.java)
  - add interface annotation (`1.15-app-add-devoxx-svc-annot`)
  - add `getConferences` method (`1.16-app-add-devoxx-getConf`)
 - create [DevoxxCFPResource](./jarvis-app/src/main/java/fr/wilda/fullstack/resources/DevoxxCFPResource.java)
  - add root path `/devoxx-talks` (`1.17-app-devoxx-resource-path`)
  - inject rest client (`1.18-app-devoxx-resource-rest-client`)
  - add `getConferences` method annotation (`1.19-app-devoxx-resource-get-conferences-annot`) 
  - add `getConferences` method body (`1.20-app-devoxx-resource-get-conf-body`)
  - test API : [1.20-test-devoxx-api.sh](./jarvis-app/1.20-test-devoxx-api.sh)
 - add the `langchain4j-ovh-ai` dependecy in the [pom.xml](./jarvis-app/pom.xml) (`1.21-app-ovhai-dependency`)
 - add `quarkus-langchain4j-pgvector, quarkus-langchain4j-easy-rag` extensions: [1.22-add-quarkus-pgvector-rag-ext.sh](./jarvis-app/1.22-add-quarkus-pgvector-rag-ext.sh) 
 - add RAG parameters in [application.properties](./jarvis-app/src/main/resources/application.properties) (`1.23-app-rag-cfg`)
 - create [RegisterOVHEmbededModel](./jarvis-app/src/main/java/fr/wilda/fullstack/config/RegisterOVHEmbededModel.java)
  - set class annotation (`1.24-app-embedding-model-annot`)
  - add the `Produces` annotation (`1.25-app-embedding-model-producer`)
  - configure the OVHcloud embedding model (`1.26-app-enbedding-model-ovh-model`)
 - create the [DevoxxDataEmbedding](./jarvis-app/src/main/java/fr/wilda/fullstack/services/DevoxxDataEmbeddingService.java) class
  - inject the embedding store and model (`1.27-app-data-embedding-svc-inject`)
  - inject the Devoxx service (`1.28-app-data-devoxx-svc-inject`)
  - ingest talks (`1.29-app-data-devoxx-svc-ingest`)
  - create the `deleteAll` method (`1.30-app-data-devoxx-svc-deleteall`)
 - update the [DevoxxCFPResource](./jarvis-app/src/main/java/fr/wilda/fullstack/resources/DevoxxCFPResource.java)
  - inject [DevoxxDataEmbeddingService](./jarvis-app/src/main/java/fr/wilda/fullstack/services/DevoxxDataEmbeddingService.java) (`1.31-app-devoxx-resource-rest-embedding`)
  - add `/synchro` and `/delete` route (`1.32-app-devoxx-resource-rest-synchro`)
  - run synchronisation: [1.33-test-synchro.sh](./jarvis-app/1.33-test-synchro.sh)
 - test again the chatbot: [1.34-test-chatbot-after-synchro.sh](./jarvis-app/1.12-test-chatbot.sh)
 - add prod configuration for DB in the [application.properties](./jarvis-app/src/main/resources/application.properties) (`1.35-app-db-prod-config`)

## 🎨 Frontend 🎨
 - add the 'quarkus-qute-web' extension [1.35-add-quarkus-qute-ext.sh](./jarvis-app/1.35-add-quarkus-qute-ext.sh)
 - create the [devoxxconference.html](./jarvis-app/src/main/resources/templates/DevoxxCFPResource/devoxxconference.html) 
  - add the HTML code to call the template (`1.36-app-devoxx-confs-html`)
 - update [DevoxxCFPResource.java](./jarvis-app/src/main/java/fr/wilda/fullstack/resources/DevoxxCFPResource.java) class
  - inject the template (`1.37-app-devoxx-resource-template-inject`)
  - create the `index` endpoint (`1.38-app-devoxx-resource-template-index`)
  - create the `template` endpoint (`1.39-app-devoxx-resource-template`)
 - test the application to display Devoxx talks: `http://localhost:8080/devoxx-talks/index`
 - dev UI: `http://localhost:8080/q/dev-ui/welcome`
 - add `quarkus-websockets-next` extension: [1.40-add-quarkus-websocket-ext.sh](./jarvis-app/1.40-add-quarkus-websocket-ext.sh)
 - create the [ChatBotWebsocketResource](./jarvis-app/src/main/java/fr/wilda/fullstack/resources/ChatBotWebsocketResource.java)
  - add the `WebSocket` annotation (`1.41-app-chatbot-ws-annot`)
  - inject `ChabotService` (`1.42-app-chatbot-ws-inject-svc`)
  - create the `onMessage` method (`1.43-app-chatbot-ws-on-message`)
  - test the chatbot: `http://localhost:8080/`

# 🧩 02 - Infrastructure as Code : [jarvis-iac](./jarvis-iac) 🧩

  - create the pulumi project: [_init/2.01-iac-create.sh](./_init/2.01-iac-create.sh)
  - create the [JarvisIaC](./jarvis-iac/src/JarvisIaC.java) class
    - add the shebank directive (`2.02-iac-shebang`)
    - add dependencies (`2.03-iac-dependencies`)
    - declare the OVHcloud service (`2.04-iac-ovhcloud-serviceId`)
    - set timeout (`2.05-iac-timeout`)
    - create the kubernetes
      - declare the main configurations (`2.06-iac-kube-details`)
      - declare the nodepool (`2.07-iac-kube-nodepool-details`)
      - export kubeconfig (`2.08-iac-kube-kubeconfig`)
    - create the database
      - delacre the main configuration (`2.09-iac-db-details`)
      - declare user for PostgreSQL (`2.10-iac-db-postgres-user`)
      - declare PostgreSQL instance (`2.11-iac-db-postgres-instance`)
      - export DB configuations (`2.12-iac-db-conf-export`)
    - call the two methods in main (`2.13-iac-call-kube`) (`2.14-iac-call-db`)
  - set new runtime configuration in [Pulumi.yaml](./jarvis-iac/Pulumi.yaml) file (`2.15-pulumi-new-runtime-cfg`)
  - add OVHcloud provider [2.16-iac-ovh-provider.sh](./jarvis-iac/2.16-iac-ovh-provider.sh)
  - run the infrastructure creation [2.17-iac-pulumi-up.sh](./jarvis-iac/2.17-iac-pulumi-up.sh)

# 🏗️ 03 - Jenkins : [Jenkinsfile](./jarvis-app/Jenkinsfile) 🏗️

  - create the [Jenkinsfile](./jarvis-app/Jenkinsfile) file
  - create a pipeline (`01-jenkins-pipeline`)
  - create the `📦 Build Quarkus app ⚡️` stage (`02-jenkins-app-build`)
  - create the `🐳 Build Docker image 🐳` stage (`03-jenkins-docker-build`)
  - create the `🏷️ Tag et Push dans GitHub 🐙` stage (`04-jenkins-github-tag`)
  - create the `🪪 Release GitHub 🪪` stage (`05-jenkins-github-release`)
  - create the post pipeline stage (` 06-jenkins-post-pipeline`)
  - run the pipeline in Jenkins (`http://51.210.251.111:8080`)

# 🤖 04 - Jarvis operator : [jarvis_operator](./jarvis_operator) 🤖

  - run [_init/4.01-operator-create.sh](./_init/4.01-operator-create.sh) 
  - update the [application.properties](./jarvis-operator/src/main/resources/application.properties) file (`4.02-configure-k8s-client`)
  - start Quarkus in dev mode in [jarvis-operator](./jarvis-operator/) folder (`4.03-start-quarkus-dev.sh`)
  - in the dev mode terminal (`:`) run `qosdk api --version v1 --kind HelloWorldOperator --group fullstack.wilda.fr` (`q` to quit)
  - update the file [kubeconfig.yml](./jarvis-operator/kubeconfig.yml) with local Kubernetes configuration
  - run [4.04-helloworld-crd-display.sh](./jarvis-operator/4.04-helloworld-crd-display.sh)
  - update [HelloWorldOperatorSpec](./jarvis-operator/src/main/java/fr/wilda/fullstack/HelloWorldOperatorSpec.java) class (`4.05-add-name-hw-spec`)
  - display CRD update (`4.06-helloworld-crd-display-update.sh`)
  - update [HelloWorldOperatorReconciler](./jarvis-operator/src/main/java/fr/wilda/fullstack/HelloWorldOperatorReconciler.java) class (`4.07-log-hw-reconcile`) & (`4.08-add-cleanup-hw-reconcile`)
  - create the test CRD [cr-test-hello-world.yaml](./jarvis-operator/src/test/cr-test-hello-world.yaml)
  - apply the CRD (`4.09-apply-cr-test-hello-world.sh`)
  - in the Quarkus terminal mode (`:`) run `qosdk api --version v1 --kind JarvisOperator --group fullstack.wilda.fr`, then quit (`q`)
  - update the [JarvisOperatorSpec](./jarvis-operator/src/main/java/fr/wilda/fullstack/JarvisOperatorSpec.java) class (`4.10-add-jarvis-operator-spec`) and [JarvisOperatorStatus](./jarvis-operator/src/main/java/fr/wilda/fullstack/JarvisOperatorStatus.java) class (`4.11-add-jarvis-operator-status`)
  - update the [JarvisOperatorReconciler](./jarvis-operator/src/main/java/fr/wilda/fullstack/JarvisOperatorReconciler.java)
    - add `makeDeployment` method (`4.14-create-deployment`)
    - add `makeService` method (`4.15-create-service`)
    - get namspace and status (`4.16-namespace-and-status`)
    - add jarvis deployment code (`4.17-deploy-jarvis`)
    - create deployment (`4.18-make-deployment`)
    - create service (`4.19-make-service`)
    - update the status (`4.20-update-status`)
    - apply the status patch (`4.21-apply-status-patch`)
    - add `fireEvent` method (`4.22-add-fire-event-method`)
  - add `quarkus-rest-jackson` extension (`4.22-add-quarkus-rest-jackson.sh`)
  - create [GHTagEvent](./jarvis-operator/src/main/java/fr/wilda/fullstack/webhook/GHTagEvent.java) class 
    - add fields in `GHTagEvent` (`4.23-add-ref-and-ref-type-fields`)
  - create [WebHook](./jarvis-operator/src/main/java/fr/wilda/fullstack/webhook/WebHook.java) class
    - inject Jarvis operator (`4.24-inject-jarvis-operator`)
    - create `newTag` method (`4.25-add-new-tag-method`)
  - test Jarvis operator
    - create secrets `devoxx-secrets` (`4.26-create-devoxx-secrets.sh`)
    - create [cr-test-gh-release-watch.yml](./jarvis-operator/src/test/resources/cr-test-gh-release-watch.yml) file
    - create `jarvis` namespace and apply `cr-test-gh-release-watch` resource (`4.27-apply-cr-test-gh-release.sh`)
    - send a test event (`4.28-send-test-event.sh`)
    - create a local port forward (`4.29-local-port-forward`)
    - whitelist local IP in the DB (`4.30-local-ip.sh`)
    - test Jarvis application (`4.31-test-local-jarvis.sh`)
  - package the operator
    - update the [application.properties](./jarvis-operator/src/main/resources/application.properties) file (`4.32-packaging-configuration`)
    - add Quarkus extension `quarkus-container-image-jib` (`4.33-add-quarkus-jib-extension.sh`)
    - package with maven the operator (`4.34-operator-packaging.sh`)
  - deploy operator on managed Kubernetes
    - build and push Docker image (`4.35-push-operator-image.sh`)
    - add [kubernetes.yml](./jarvis-operator/src/main/kubernetes/kubernetes.yml) file 
    - remove `docker.io` from [kubernetes.yml](./jarvis-operator/target/kubernetes/kubernetes.yml)
    - create namespaces in Kubernetes (`4.36-create-mks-ns.sh`)
    - create CRDs in managed Kubernetes (`4.37-create-mks-crds`)
    - deploy the operator (`4.38-deploy-operator-on-mks`)
  - test the operator
    - send a "github" release (`4.41-test-mks-operator.sh`)
    - update GH Webhook with LB IP
    - launch a Jenkins release
  - test Jarvis
    - whitelist the managed kubernetes on the DB
    - run [4.42-test-mks-jarvis.sh](./jarvis-operator/4.42-test-mks-jarvis.sh)

# 05 - 💻 Jarvis CLI 💻

  - run [5.01-cli-create.sh](./_init/5.01-cli-create.sh)
  - run [5.02-check-dependencies.sh](./jarvis-cli/5.02-check-dependencies.sh)
  - run [5.03-start-quarkus-dev.sh](./jarvis-cli/5.03-start-quarkus-dev.sh) and test default CLI
  - create [JarvisAPIService](./jarvis-cli/src/main/java/fr/wilda/fullstack/sdk/JarvisAPIService.java) interface
    - add the annotation (`5.04-svc-annotation`)
    - add endpoints call (`5.05-svc-endpoint`)
    - update the [application.properties](./jarvis-cli/src/main/resources/application.properties) file (`5.06-rest-client-cfg`)
  - create [JarvisCommand](./jarvis-cli/src/main/java/fr/wilda/fullstack/JarvisCommand.java) class
    - add `@Command` annotation (`5.07-jarvis-annot`)
    - inject rest client annotation (`5.08-jarvis-rest-client`)
    - add parameter (`5.09-jarvis-param`)
    - add `call` method (`5.10-call-service`)
    - add `@TopCommand` annotation
  - test the CLI `Est-ce que Stéphane Philippart a un talk à Devoxx France ?`
  - create [DevoxxSubCommand](./jarvis-cli/src/main/java/fr/wilda/fullstack/DevoxxSubCommand.java) class
    - add the annotation (`5.11-devoxx-svc-annotation`)
    - inject rest client annotation (`5.12-devoxx-rest-client`)
    - add options (`5.13-devoxx-options`)
    - add `call` (`5.14-devoxx-call`)
  - add `DevoxxSubCommand` to the `JarvisCommand` class
  - create native CLI
    - set logs (`5.15-prod-logs`)
    - run [5.16-create-native-cli.sh](./jarvis-cli/5.16-create-native-cli.sh)
    - test CLI 
  