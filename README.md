# ☕️ Java full stack conference 🏗️
Code source for the Java full stack conference

# 🧑‍💻 How to use / contribute 🐳

The simpliest way to use this code is to re-open the project with the Dev Containers.

# ⚡️ 01 - Quarkus app : Jarvis-App ⚡️
## 📼 Backend 📼

 - run [_init/01-app-create.sh](./_init/)
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
 - add `quarkus-websockets-next` extension (`33-add-quarkus-websockets-ext`)
 - create the [ChatBotWebsocketResource](./jarvis_app/src/main/java/fr/wilda/fullstack/resources/ChatBotWebsocketResource.java)
  - add the `WebSocket` annotation (`34-app-chatbot-ws-annot`)
  - inject `ChabotService` (`35-app-chatbot-ws-inject-svc`)
  - create the `onMessage` method (`36-app-chatbot-ws-on-message`)
  - test the websocket 