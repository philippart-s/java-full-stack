package fr.wilda.fullstack.resources;

import java.util.Collections;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.wilda.fullstack.dto.Conference;
import fr.wilda.fullstack.services.DevoxxCFPService;
import fr.wilda.fullstack.services.DevoxxDataEmbeddingService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// 1.17-app-devoxx-resource-path
@Path("/devoxx-talks")
public class DevoxxCFPResource {
  private static final Logger _LOG = LoggerFactory.getLogger(DevoxxCFPResource.class);

  // 1.18-app-devoxx-resource-rest-client
  @RestClient
  DevoxxCFPService devoxxCFP;

  // 1.31-app-devoxx-resource-rest-embedding
  @Inject
  DevoxxDataEmbeddingService devoxxDataEmbedding;

  // 1.37-app-devoxx-resource-template-inject
  @Inject
  @Location("DevoxxCFPResource/devoxxconference.html")
  Template devoxxconference;

  // 1.19-app-devoxx-resource-get-conferences-annot
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Conference> getConferences() {
    // 1.20-app-devoxx-resource-get-conf-body
    List<Conference> devoxxConferences = devoxxCFP.getConferences();

    _LOG.info("Nombre de conférences à Devoxx : {}", devoxxConferences.size());

    return devoxxConferences;
  }

  // 1.38-app-devoxx-resource-template-index
  @GET
  @Path("/index")
  @Produces(MediaType.TEXT_HTML)
  @Blocking
  public TemplateInstance getIndex() throws Exception {

    return devoxxconference.data("conferences", Collections.EMPTY_LIST);
  }

  // 1.39-app-devoxx-resource-template
  @GET
  @Path("/template")
  @Produces(MediaType.TEXT_HTML)
  @Blocking
  public TemplateInstance getConferencesTemplate() throws Exception {

    return devoxxconference.data("conferences", devoxxCFP.getConferences().subList(0, 10));
  }

  // 1.32-app-devoxx-resource-rest-synchro
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
