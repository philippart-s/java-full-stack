package fr.wilda.fullstack.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.wilda.fullstack.services.ChatbotService;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// 07-app-chatbot-rest-path
@Path("chatbot-api")
public class ChatbotRestResource {
  // 08-app-chatbot-rest-logger
  private static final Logger _LOG = LoggerFactory.getLogger(ChatbotRestResource.class);

  // 08-app-chatbot-rest-client
  @Inject
  ChatbotService chatbotService;
  
  // 09-app-chatbot-rest-ask-annot
  @POST
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.TEXT_PLAIN)
  public Multi<String> ask(String question) {
    // 10-app-chatbot-rest-call-model
    return chatbotService.askAQuestion(question);
  }
}
