package fr.wilda.fullstack.services;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import fr.wilda.fullstack.dto.Conference;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DevoxxDataEmbeddingService {
  private static final Logger _LOG = LoggerFactory.getLogger(DevoxxDataEmbeddingService.class);

  // 1.28-app-data-embedding-svc-inject
  @Inject
  EmbeddingModel embeddingModel;

  @Inject
  EmbeddingStore<TextSegment> embeddingStore;

  // 1.29-app-data-devoxx-svc-inject
  @RestClient
  DevoxxCFPService devoxxCFP;

  /**
   * Take the Devoxx talks and transform them into a vector to add each of vectors in the store.
   */
  public void ingest() {
    // 1.30-app-data-devoxx-svc-ingest
    List<Conference> talks = devoxxCFP.getConferences();

    for (Conference conference : talks) {
      _LOG.info("Talk ingéré : {}", conference.getTitle());
      embeddingStore.add(embeddingModel
          .embed(conference.getSpeakers().toString())
          .content(),
          TextSegment.from(conference.toString()));
    }
  }

  /**
   * Delete all vectors in the store.
   */
  public void deleteAll() {
    // 1.31-app-data-devoxx-svc-deleteall
    embeddingStore.removeAll();
  }
}
