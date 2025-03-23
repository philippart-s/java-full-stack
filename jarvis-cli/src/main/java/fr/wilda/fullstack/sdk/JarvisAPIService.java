package fr.wilda.fullstack.sdk;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// 5.04-svc-annotation
@ClientHeaderParam(name = "Content-Type", value = MediaType.TEXT_PLAIN)
@RegisterRestClient
public interface JarvisAPIService {

  // 5.05-svc-endpoint
  @POST
  @Path("/chatbot-api")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Multi<String> askJarvis(@FormParam("message") String message);

  @GET
  @Path("/devoxx-synchro")
  public Response embeddingSynchro();

  @GET
  @Path("/devoxx-synchro/delete")
  public Response embeddingDeletion();
}
