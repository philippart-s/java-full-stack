package fr.wilda.fullstack.resources;

import fr.wilda.fullstack.services.ChatbotService;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;

// 34-app-chatbot-ws-annot
@WebSocket(path = "/chatbot-ws")
public class ChatBotWebsocketResource {

  // 35-app-chatbot-ws-inject-svc
  @Inject
  ChatbotService chatbotService;

  // 36-app-chatbot-ws-on-message
  @OnTextMessage
  public Multi<String> onMessage(String message) {
    return chatbotService.askAQuestion(message);
  }

}
