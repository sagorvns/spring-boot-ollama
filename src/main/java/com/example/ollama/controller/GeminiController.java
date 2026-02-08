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

/**
 * REST Controller for Gemini AI endpoints.
 * Provides API endpoints to interact with Google Gemini AI.
 */
@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private static final Logger logger = LoggerFactory.getLogger(GeminiController.class);
    
    private final GeminiService geminiService;

    /**
     * Constructor injection of GeminiService
     * @param geminiService The Gemini service
     */
    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    /**
     * Endpoint to ask a question to Gemini AI
     * @param request The prompt request containing the user's question
     * @return Mono with ResponseEntity containing the AI-generated response
     */
    @PostMapping("/ask")
    public Mono<ResponseEntity<Map<String, String>>> ask(@RequestBody PromptRequest request) {
        logger.info("Received Gemini request with prompt: {}", request.getPrompt());
        
        // Validate input
        if (request.getPrompt() == null || request.getPrompt().trim().isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Prompt cannot be empty");
            return Mono.just(ResponseEntity.badRequest().body(errorResponse));
        }
        
        // Generate response from Gemini
        return geminiService.generateResponse(request.getPrompt())
                .map(response -> {
                    // Build success response
                    Map<String, String> responseMap = new HashMap<>();
                    responseMap.put("prompt", request.getPrompt());
                    responseMap.put("response", response);
                    responseMap.put("model", "gemini-2.0-flash-exp");
                    responseMap.put("provider", "Google Gemini");
                    return ResponseEntity.ok(responseMap);
                })
                .onErrorResume(error -> {
                    // Handle errors
                    logger.error("Error processing Gemini request: {}", error.getMessage(), error);
                    Map<String, String> errorResponse = new HashMap<>();
                    errorResponse.put("error", "Failed to process request: " + error.getMessage());
                    return Mono.just(ResponseEntity.internalServerError().body(errorResponse));
                });
    }

    /**
     * Health check endpoint for Gemini service
     * @return Status of the Gemini service
     */
    @GetMapping("/health")
    public Mono<ResponseEntity<Map<String, String>>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "Gemini AI");
        status.put("model", "gemini-2.0-flash-exp");
        status.put("provider", "Google");
        return Mono.just(ResponseEntity.ok(status));
    }
}
