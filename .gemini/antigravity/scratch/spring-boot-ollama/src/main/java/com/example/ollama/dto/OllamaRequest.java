package com.example.ollama.dto;

public record OllamaRequest(String model, String prompt, boolean stream, double temperature) {
}
