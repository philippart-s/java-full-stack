package fr.wilda.fullstack.resources;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.wilda.fullstack.dto.Conference;
import fr.wilda.fullstack.services.DevoxxCFPService;
import fr.wilda.fullstack.services.DevoxxDataEmbeddingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// 15-app-devoxx-resource-path
@Path("/devoxx-talks")
public class DevoxxCFPResource {
  // 16-app-devoxx-resource-logger
  private static final Logger _LOG = LoggerFactory.getLogger(DevoxxCFPResource.class);

  // 17-app-devoxx-resource-rest-client
  @RestClient
  DevoxxCFPService devoxxCFP;

  // 30-app-devoxx-resource-rest-embedding
  @Inject
  DevoxxDataEmbeddingService devoxxDataEmbedding;

  // 18-app-devoxx-resource-get-conferences-annot
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Conference> getConferences() {
    // 19-app-devoxx-resource-get-conf-body
    List<Conference> devoxxConferences = devoxxCFP.getConferences();

    _LOG.info("Nombre de conférences à Devoxx : {}", devoxxConferences.size());

    return devoxxConferences;
  }

  // 31-app-devoxx-resource-rest-synchro
  @GET
  @Path("/synchro")
  public void synchro() {
    devoxxDataEmbedding.ingest();
  }

  @GET
  @Path("/delete")
  public void deleteAll() {
    devoxxDataEmbedding.deleteAll();

  }
}
