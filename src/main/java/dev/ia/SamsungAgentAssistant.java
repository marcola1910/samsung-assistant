package dev.ia;

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface SamsungAgentAssistant {

    /**
     * Recebe a pergunta do usuário e retorna a resposta do LLM.
     * Pode ser expandido com tool calls, memória, etc.
     */
    String chat(String userMessage);
}
