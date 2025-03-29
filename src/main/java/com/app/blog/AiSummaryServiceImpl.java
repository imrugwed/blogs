package com.app.blog;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AiSummaryServiceImpl implements AiSummaryService {


    private final RestTemplate restTemplate = new RestTemplate();
    //    @Value("${google.gemini.api.key}")
    private String googleApiKey = "AIzaSyCG2fMUac0Mo9n2ppRwQTXErgiy6T8TON4";

    @Override
    public Object summarizeBlog(String content) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + googleApiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", "Summarize the following blog:\n\n" + content)
                        })
                }
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        if (response.getBody() != null && response.getBody().containsKey("candidates")) {
            return response.getBody().get("candidates");
        }


//        return extractSummary(response.getBody());
        return  "Failed to generate summary.";
    }


    private String extractSummary(Map<String, Object> response) {
        try {
            return ((List<Map<String, Object>>) response.get("candidates"))
                    .stream()
                    .map(c -> (Map<String, Object>) c.get("content"))
                    .map(c -> (List<Map<String, Object>>) c.get("parts"))
                    .flatMap(List::stream)
                    .map(p -> (String) p.get("text"))
                    .findFirst()
                    .orElse("No summary available.");
        } catch (Exception e) {
            return "Failed to generate summary.";
        }
    }

}