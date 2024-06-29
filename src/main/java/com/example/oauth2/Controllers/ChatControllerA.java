package com.example.oauth2.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatControllerA {

    @Value("${openai.api.key}")
    private String apiKey;

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody Map<String, String> requestBody) {
        try {
            String message = requestBody.get("message");
            if (message == null || message.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message is required");
            }

            String url = "https://api.openai.com/v1/chat/completions";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Content-Type", "application/json");

            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("model", "gpt-3.5-turbo");
            requestMap.put("messages", new Object[]{new HashMap<String, String>() {{
                put("role", "user");
                put("content", message);
            }}});
            requestMap.put("max_tokens", 150);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestMap, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Transform the response to the expected structure
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());
            String content = jsonResponse.get("choices").get(0).get("message").get("content").asText();

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("response", content);

            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while processing the request", e);
        }
    }
}
