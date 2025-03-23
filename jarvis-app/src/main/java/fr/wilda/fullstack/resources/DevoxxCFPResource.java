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
public class DevoxxCFPResource {
  private static final Logger _LOG = LoggerFactory.getLogger(DevoxxCFPResource.class);

  // 1.18-app-devoxx-resource-rest-client

  // 1.31-app-devoxx-resource-rest-embedding
  
  // 1.38-app-devoxx-resource-template-inject
  
  // 1.19-app-devoxx-resource-get-conferences-annot
  public List<Conference> getConferences() {
    // 1.20-app-devoxx-resource-get-conf-body
  }

  // 1.39-app-devoxx-resource-template-index
  
  // 1.40-app-devoxx-resource-template
  
  // 1.32-app-devoxx-resource-rest-synchro
  
}
