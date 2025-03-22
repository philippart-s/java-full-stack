# ☕️ Java full stack conference 🏗️
Code source for the Java full stack conference

# 🧑‍💻 How to use / contribute 🐳

The simpliest way to use this code is to re-open the project with the Dev Containers.

# ⚡️ 01 - Quarkus app : [jarvis_app](./jarvis_app/) ⚡️
## 📼 Backend 📼

 - run [_init/01-app-create.sh](./_init/01-app-create.sh)
 - run `quarkus dev` in `jarvis-app` (`02-start-quarkus-dev.sh`)
 - `curl http://localhost:8080/hello`
 - connect to the developer console: `http://localhost:8080`
 - add `quarkus-langchain4j-mistral-ai` extension (`03-add-quarkus-langchain-ext.sh`)
 - configure extension in [application.properties](./jarvis_app/src/main/resources/application.properties) file (`04-app-langchain4j-cfg`)
 - create [ChatbotService](./jarvis_app/src/main/java/fr/wilda/fullstack/services/ChatbotService.java) interface
  - add AI interface annotations (`05-app-ai-service`)
  - add AI interface prompts (`06-app-ai-prompts`)
 - create [ChatbotRestResource](./jarvis_app/src/main/java/fr/wilda/fullstack/resources/ChatbotRestResource.java) class
  - add path (`07-app-chatbot-rest-path`)
  - add logger (`08-app-chatbot-rest-logger`)
  - inject rest client (`08-app-chatbot-rest-client`)
  - add `ask` method anotions (`09-app-chatbot-rest-ask-annot`)
  - call the model (`10-app-chatbot-rest-call-model`)
 - test the endpoint: `curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire ce qu'est la conférence Devoxx France s'il te plaît ?" http://localhost:8080/chatbot-api`
 - ask for recent information about Devoxx: `curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire si Stéphane Philippart a des talks à Devoxx ?" http://localhost:8080/chatbot-api`
 - add `quarkus-rest-jackson` and `quarkus-rest-client-jackson` extension and remove the `quarkus-rest` extension: `11-add-quarkus-rest-ext.sh`
 - add rest client configuration in [application.properties](./jarvis_app/src/main/resources/application.properties) (`12-app-rest-client-cfg`)
 - create [DevoxxCFPService](./jarvis_app/src/main/java/fr/wilda/fullstack/services/DevoxxCFPService.java)
  - add interface annotation (`13-app-add-devoxx-svc-annot`)
  - add `getConferences` method (`14-app-add-devoxx-getConf`)
 - create [DevoxxCFPResource](./jarvis_app/src/main/java/fr/wilda/fullstack/resources/DevoxxCFPResource.java)
  - add root path `/devoxx-talks` (`15-app-devoxx-resource-path`)
  - add logger (`16-app-devoxx-resource-logger`)
  - inject rest client (`17-app-devoxx-resource-rest-client`)
  - add `getConferences` method annotation (`18-app-devoxx-resource-get-conferences`) 
  - add `getConferences` method body (`19-app-devoxx-resource-get-conf-body`)
  - test API : `curl http://localhost:8080/devoxx-talks`
 - add the `langchain4j-ovh-ai` dependecy in the [pom.xml](./jarvis_app/pom.xml) (`20-app-ovhai-dependency`)
 - add `quarkus-langchain4j-pgvector, quarkus-langchain4j-easy-rag` extensions (`21-add-quarkus-pgvector-rag-ext.sh`)
 - add RAG parameters in [application.properties](./jarvis_app/src/main/resources/application.properties) (`22-app-rag-cfg`)
 - create [RegisterOVHEmbededModel](./jarvis_app/src/main/java/fr/wilda/fullstack/config/RegisterOVHEmbededModel.java)
  - set class annotation (`23-app-embedding-model-annot`)
  - add the `Produces` annotation (`24-app-embedding-model-producer`)
  - configure the OVHcloud embedding model (`25-app-enbedding-model-ovh-model`)
 - create the [DevoxxDataEmbedding](./jarvis_app/src/main/java/fr/wilda/fullstack/services/DevoxxDataEmbedding.java) class
  - inject the embedding store and model (`26-app-data-embedding-svc-inject`)
  - inject the Devoxx service (`27-app-data-devoxx-svc-inject`)
  - ingest talks (`28-app-data-devoxx-svc-ingest`)
  - create the `deleteAll` method (`29-app-data-devoxx-svc-deleteall`)
 - update the [DevoxxCFPResource](./jarvis_app/src/main/java/fr/wilda/fullstack/resources/DevoxxCFPResource.java)
  - inject [DevoxxDataEmbeddingService](./jarvis_app/src/main/java/fr/wilda/fullstack/services/DevoxxDataEmbeddingService.java) (`30-app-devoxx-resource-rest-embedding`)
  - add `/synchro` and `/delete` route (`31-app-devoxx-resource-rest-synchro`)
  - run synchronisation: `curl http://localhost:8080/devoxx-talks/synchro`
 - test again the chatbot: `curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire si Stéphane Philippart a des talks à Devoxx ?" http://localhost:8080/chatbot-api`
 - add prod configuration for DB in the [application.properties](./jarvis_app/src/main/resources/application.properties) (`32-app-db-prod-config`)

## 🎨 Frontend 🎨
 - add the 'quarkus-qute-web' extension (`33-add-quarkus-qute-ext.sh`)
 - create the [devoxxconference.html](./jarvis_app/src/main/resources/templates/DevoxxCFPResource/devoxxconference.html) 
  - add the HTML code to call the template (`34-app-devoxx-confs-html`)
 - update [DevoxxCFPResource.java](./jarvis_app/src/main/java/fr/wilda/fullstack/resources/DevoxxCFPResource.java) class
  - inject the template (`35-app-devoxx-resource-template-inject`)
  - create the `index` endpoint (`36-app-devoxx-resource-template-index`)
  - create the `template` endpoint (`37-app-devoxx-resource-template`)
 - test the application to display Devoxx talks: `http://localhost:8080/devoxx-talks/index`
 - dev UI: `http://localhost:8080/q/dev-ui/welcome`
 - add `quarkus-websockets-next` extension (`38-add-quarkus-websockets-ext.sh`)
 - create the [ChatBotWebsocketResource](./jarvis_app/src/main/java/fr/wilda/fullstack/resources/ChatBotWebsocketResource.java)
  - add the `WebSocket` annotation (`39-app-chatbot-ws-annot`)
  - inject `ChabotService` (`40-app-chatbot-ws-inject-svc`)
  - create the `onMessage` method (`41-app-chatbot-ws-on-message`)
  - test the chatbot: `http://localhost:8080/`

# 🧩 02 - Infrastructure as Code : [jarvis_iac](./jarvis_iac) 🧩

  - create the pulumi project: [_init/42-iac-create.sh](./_init/42-iac-create.sh)
  - create the [JarvisIaC](./jarvis_iac/src/JarvisIaC.java) class
    - add the shebank directive (`01-iac-shebang`)
    - add dependencies (`02-iac-dependencies`)
    - declare the OVHcloud service (`03-iac-ovhcloud-serviceId`)
    - set timeout (`04-iac-timeout`)
    - create the kubernetes
      - declare the main configurations (`05-iac-kube-details`)
      - declare the nodepool (`06-iac-kube-nodepool-details`)
      - export kubeconfig (`07-iac-kube-kubeconfig`)
    - create the database
      - delacre the main configuration (`08-iac-db-details`)
      - declare user for PostgreSQL (`09-iac-db-postgres-user`)
      - declare PostgreSQL instance (`10-iac-db-postgres-instance`)
      - export DB configuations (`11-iac-db-conf-export`)
    - call the two methods in main (`12-iac-call-kube`) (`13-iac-call-db`)
  - set new runtime configuration in [Pulumi.yaml](./jarvis_iac/Pulumi.yaml) file (`14-pulumi-new-runtime-cfg`)
  - add OVHcloud provider (`15-iac-ovh-provider.sh`)
  - run the infrastructure creation (`16-iac-pulumi-up`)

# 🏗️ 03 - Jenkins : [Jenkinsfile](./jarvis_app/Jenkinsfile) 🏗️

  - create the [Jenkinsfile](./jarvis_app/Jenkinsfile) file
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
    - 


  
  