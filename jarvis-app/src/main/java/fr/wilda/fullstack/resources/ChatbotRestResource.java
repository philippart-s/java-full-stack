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

// 1.08-app-chatbot-rest-path
@Path("chatbot-api")
public class ChatbotRestResource {
  private static final Logger _LOG = LoggerFactory.getLogger(ChatbotRestResource.class);

  // 1.09-app-chatbot-inject-svc
  @Inject
  ChatbotService chatbotService;
  
  // 1.10-app-chatbot-rest-ask-annot
  @POST
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.TEXT_PLAIN)
  public Multi<String> ask(String question) {
    // 1.11-app-chatbot-rest-call-model
    return chatbotService.askAQuestion(question);
  }
}
