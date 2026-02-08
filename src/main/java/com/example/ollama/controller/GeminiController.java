package com.example.ollama.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ollama.dto.PromptRequest;
import com.example.ollama.service.GeminiService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private static final Logger logger = LoggerFactory.getLogger(GeminiController.class);
    
    // Using a stable model name to avoid the 404 error from experimental endpoints
    private static final String MODEL_NAME = "gemini-1.5-flash"; 
    
    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/ask")
    public Mono<ResponseEntity<Map<String, String>>> ask(@RequestBody PromptRequest request) {
        logger.info("Received Gemini request with prompt: {}", request.getPrompt());
        
        if (request.getPrompt() == null || request.getPrompt().trim().isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Prompt cannot be empty");
            return Mono.just(ResponseEntity.badRequest().body(errorResponse));
        }
        
        return geminiService.generateResponse(request.getPrompt())
                .map(response -> {
                    Map<String, String> responseMap = new HashMap<>();
                    responseMap.put("prompt", request.getPrompt());
                    responseMap.put("response", response);
                    responseMap.put("model", MODEL_NAME);
                    responseMap.put("provider", "Google Gemini");
                    return ResponseEntity.ok(responseMap);
                })
                .onErrorResume(error -> {
                    logger.error("Error processing Gemini request: {}", error.getMessage(), error);
                    Map<String, String> errorResponse = new HashMap<>();
                    // The 404 error was likely due to the "exp" suffix in the URL
                    errorResponse.put("error", "API Error: " + error.getMessage());
                    return Mono.just(ResponseEntity.internalServerError().body(errorResponse));
                });
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<Map<String, String>>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "Gemini AI");
        status.put("model", MODEL_NAME);
        status.put("provider", "Google");
        return Mono.just(ResponseEntity.ok(status));
    }
}