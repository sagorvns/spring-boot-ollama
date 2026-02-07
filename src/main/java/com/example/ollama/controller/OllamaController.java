package com.example.ollama.controller;

import com.example.ollama.dto.OllamaResponse;
import com.example.ollama.dto.PromptRequest;
import com.example.ollama.service.OllamaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OllamaController {

    private final OllamaService ollamaService;

    public OllamaController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/ask")
    public Mono<OllamaResponse> ask(@RequestBody PromptRequest request) {
        return ollamaService.generateResponse(request.getPrompt());
    }
}
