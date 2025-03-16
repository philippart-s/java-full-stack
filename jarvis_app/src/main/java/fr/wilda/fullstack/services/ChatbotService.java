package fr.wilda.fullstack.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;

// 05-app-ai-service
@RegisterAiService
@ApplicationScoped
public interface ChatbotService {
  // 06-app-ai-prompts
  @SystemMessage("""
      Tu es un spécialiste de la conférence Devoxx France 2025.
      Cette conférence propose des talks (le terme conférence est bon aussi) avec des oratrices et orateurs (le terme speaker est aussi valide).
      Lorsque tu as des questions sur les titres, essai de trouver le titre de la conférence qui s'en rapproche le plus.
      Lorsque tu as des questions sur les personnes donnant la conférence essaie de trouver le nom et prénom qui se suivent dans la catégorie speaker des données que tu possèdes.
      """)
  @UserMessage("Voci la question qui t'es posée : {question}")
  Multi<String> askAQuestion(String question);
}
