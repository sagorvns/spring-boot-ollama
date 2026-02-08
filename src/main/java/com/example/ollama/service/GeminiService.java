package com.example.ollama.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

/**
 * Service for interacting with Google Gemini AI API using direct REST calls.
 * Provides methods to generate AI responses based on user prompts.
 */
@Service
public class GeminiService {

    private static final Logger logger = LoggerFactory.getLogger(GeminiService.class);
    
    private final WebClient webClient;
    private final String apiKey;
    private final String model;
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructor with dependency injection
     * @param webClientBuilder WebClient builder
     * @param apiKey Gemini API key from properties
     * @param model Gemini model name from properties
     */
    public GeminiService(
            WebClient.Builder webClientBuilder,
            @Value("${spring.ai.google.genai.api-key}") String apiKey,
            @Value("${spring.ai.google.genai.chat.options.model:gemini-1.5-flash}") String model) {
        this.webClient = webClientBuilder.build();
        this.apiKey = apiKey;
        this.model = model;
    }

    /**
     * Generate a response from Gemini based on the user's prompt
     * @param userPrompt The user's question or prompt
     * @return Mono with the AI-generated response
     */
    public Mono<String> generateResponse(String userPrompt) {
        try {
            logger.debug("Sending prompt to Gemini: {}", userPrompt);
            
            // Build the API URL - using v1beta endpoint for newer models
            String url = String.format(
                "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent?key=%s",
                model, apiKey
            );
            
            // Build the request body according to Gemini API format
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> part = new HashMap<>();
            part.put("text", userPrompt);
            
            Map<String, Object> content = new HashMap<>();
            content.put("parts", List.of(part));
            
            requestBody.put("contents", List.of(content));
            
            // Make the API call
            return webClient.post()
                    .uri(url)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(this::extractTextFromResponse)
                    .doOnSuccess(response -> logger.debug("Received response from Gemini: {}", response))
                    .doOnError(error -> logger.error("Error calling Gemini API: {}", error.getMessage(), error));
            
        } catch (Exception e) {
            logger.error("Error preparing Gemini API request: {}", e.getMessage(), e);
            return Mono.error(new RuntimeException("Failed to generate response from Gemini: " + e.getMessage(), e));
        }
    }
    
    /**
     * Extract the generated text from Gemini API response
     * @param jsonResponse The JSON response from Gemini
     * @return The extracted text
     */
    private String extractTextFromResponse(String jsonResponse) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode candidates = root.path("candidates");
            
            if (candidates.isArray() && candidates.size() > 0) {
                JsonNode firstCandidate = candidates.get(0);
                JsonNode content = firstCandidate.path("content");
                JsonNode parts = content.path("parts");
                
                if (parts.isArray() && parts.size() > 0) {
                    return parts.get(0).path("text").asText();
                }
            }
            
            logger.warn("Unexpected response format from Gemini API");
            return "Unable to extract response from Gemini API";
            
        } catch (Exception e) {
            logger.error("Error parsing Gemini response: {}", e.getMessage(), e);
            return "Error parsing Gemini response: " + e.getMessage();
        }
    }
}
