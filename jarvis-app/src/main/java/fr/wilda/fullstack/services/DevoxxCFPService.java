package fr.wilda.fullstack.services;

import java.util.List;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.wilda.fullstack.dto.Conference;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

// 1.15-app-add-devoxx-svc-annot
@RegisterRestClient
@ClientHeaderParam(name = "Content-Type", value = "application/json")
public interface DevoxxCFPService {

  // 1.16-app-add-devoxx-getConf
  @GET
  @Path("talks")
  public List<Conference> getConferences();
}
