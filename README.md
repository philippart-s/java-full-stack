# ☕️ Java full stack conference 🏗️
Code source for the Java full stack conference

# 🧑‍💻 How to use / contribute 🐳

The simpliest way to use this code is to re-open the project with the Dev Containers.

# ⚡️ 01 - Quarkus app : Jarvis-App ⚡️

 - run [_init/01-app-create.sh](./_init/)
 - run `quarkus dev` in `jarvis-app`
 - `curl http://localhost:8080/hello`
 - connect to the developer console: `http://localhost:8080`
 - add `quarkus-rest-jackson` extension: `quarkus ext add quarkus-rest-jackson`
 - remove `quarkus-rest` extension: `quarkus ext remove quarkus-rest`
 - add rest client configuration in [application.properties](./jarvis_app/src/main/resources/application.properties) (`05-app-rest-client-cfg`)
 - create [DevoxxCFPService](./jarvis_app/src/main/java/fr/wilda/fullstack/services/DevoxxCFPService.java)
  - add interface annotation (`06-app-add-devoxx-svc-annot`)
  - add `getConferences` method (`07-app-add-devoxx-getConf`)
 - create [DevoxxCFPResource](./jarvis_app/src/main/java/fr/wilda/fullstack/resources/DevoxxCFPResource.java)
  - add root path `/devoxx-talks` (`08-app-devoxx-resource-path`)
  - add logger (`09-app-devoxx-resource-logger`)
  - inject rest client (`10-app-devoxx-resource-rest-client`)
  - add `getConferences` method annotation (`11-app-devoxx-resource-get-conferences`) 
  - add `getConferences` method body (`12-app-devoxx-resource-get-conf-body`)
  - test API : `curl `