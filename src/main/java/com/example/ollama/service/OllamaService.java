package com.example.ollama.service;

import com.example.ollama.dto.OllamaRequest;
import com.example.ollama.dto.OllamaResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OllamaService {

    private final WebClient webClient;

    public OllamaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:11434").build();
    }

    public Mono<OllamaResponse> generateResponse(String prompt) {
        OllamaRequest request = new OllamaRequest("deepseek-r1:1.5b", prompt, false, 0.3);

        return webClient.post()
                .uri("/api/generate")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OllamaResponse.class)
                .onErrorResume(e -> {
                    // Log error and return a friendly message or rethrow
                    System.err.println("Error calling Ollama API: " + e.getMessage());
                    return Mono.error(new RuntimeException("Failed to get response from Ollama: " + e.getMessage()));
                });
    }
}
