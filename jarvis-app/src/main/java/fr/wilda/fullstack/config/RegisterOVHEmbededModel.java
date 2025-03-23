package fr.wilda.fullstack.config;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ovhai.OvhAiEmbeddingModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

// 1.24-app-embedding-model-annot
@ApplicationScoped
public class RegisterOVHEmbededModel {

  // 1.25-app-embedding-model-producer
  @Produces
  public EmbeddingModel ovhAIEmbedingModel() {
    // 1.26-app-enbedding-model-ovh-model
    return OvhAiEmbeddingModel.builder()
        .baseUrl(System.getenv("OVH_AI_EMBEDDING_MODEL_URL"))
        .apiKey(System.getenv("OVH_AI_ENDPOINTS_ACCESS_TOKEN"))
        .logRequests(false)
        .logResponses(false)
        .build();
  }
}
