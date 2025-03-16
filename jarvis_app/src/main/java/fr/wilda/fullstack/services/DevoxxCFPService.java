package fr.wilda.fullstack.services;

import java.util.List;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.wilda.fullstack.dto.Conference;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

// 06-app-add-devoxx-svc-annot
@RegisterRestClient(baseUri = "https://devoxxfr2025.cfp.dev/api/public")
@ClientHeaderParam(name = "Content-Type", value = "application/json")
public interface DevoxxCFPService {

  // 07-app-add-devoxx-getConf
  @GET
  @Path("talks")
  public List<Conference> getConferences();
}
