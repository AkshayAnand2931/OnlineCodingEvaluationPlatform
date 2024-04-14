package com.example.OnlineCodingEvaluationPlatform.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class Compiler {


    @Value("${judge0.api.endpoint}")
    private String judge0endpoint;

    @Value("${judge0.api.key}")
    private String judge0ApiKey;

    @Value("${judge0.api.host}")
    private String judge0ApiHost;

    private final ObjectMapper objectMapper;

    public Compiler(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<String> compile(String sourceCode,String language, String input, String expectedOutput) {

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("language_id",getLanguageId(language));
        requestBody.put("source_code", Base64.getEncoder().encodeToString(sourceCode.getBytes()));
        requestBody.put("stdin",Base64.getEncoder().encodeToString(input.getBytes()));
        requestBody.put("expected_output",Base64.getEncoder().encodeToString(expectedOutput.getBytes()));


        AsyncHttpClient client = new DefaultAsyncHttpClient();
        return client.prepare("POST",judge0endpoint + "?base64_encoded=true&fields=*")
                .setHeader("Content-Type", "application/json")
                .setHeader("X-RapidAPI-Key",judge0ApiKey)
                .setHeader("X-RapidAPI-Host",judge0ApiHost)
                .setBody(writeValueAsString(requestBody))
                .execute()
                .toCompletableFuture()
                .thenApply(response -> {
                    try {
                        Map<String, String> responseMap = objectMapper.readValue(response.getResponseBody(), Map.class);
                        return responseMap.get("token");
                    } catch (IOException e) {
                        throw new RuntimeException("Error parsing compilation result", e);
                    }
                });
    }

    private String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing request body", e);
        }
    }

    public CompletableFuture<CompilationResult> getSubmission(String submissionToken) {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        return client.prepare("GET", judge0endpoint + "/" + submissionToken + "?base64_encoded=true&fields=*")
                .setHeader("X-RapidAPI-Key", judge0ApiKey)
                .setHeader("X-RapidAPI-Host", judge0ApiHost)
                .execute()
                .toCompletableFuture()
                .thenApply(response -> {
                    try {
                        return objectMapper.readValue(response.getResponseBody(), CompilationResult.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Error parsing compilation result", e);
                    }
                });
    }

    private int getLanguageId(String language) {
        // Implement a mapping of language names to their corresponding IDs
        // in the Judge0 API
        switch (language) {
            case "cpp":
                return 52;
            case "c":
                return 48;
            case "java":
                return 62;
            case "python":
                return 71;
            default:
                throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }
}
