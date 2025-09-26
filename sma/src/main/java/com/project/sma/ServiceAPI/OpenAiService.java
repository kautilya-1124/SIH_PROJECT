package com.project.sma.ServiceAPI;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.sma.Model.FarmInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;

@Service
public class OpenAiService {

    private static final String OPENAI_API_KEY = "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"; // 🔑 Add your OpenAI API key
    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    public static String getRecommendation(FarmInfo farmInfo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_API_KEY);

        String prompt = String.format(
        	    "A farmer has farm details: pincode=%s, soilType=%s, irrigationType=%s, waterResource=%s, area=%.2f acres. " +
        	    "Give smart recommendation strictly in JSON format with keys: cropRecommendation, soilAdvice, irrigationAdvice, fertilizerAdvice.",
        	    farmInfo.getPincode(),
        	    farmInfo.getSoilType(),
        	    farmInfo.getIrrigationType(),
        	    farmInfo.getWaterResource(),
        	    farmInfo.getArea()
        	);


        Map<String, Object> requestBody = Map.of(
            "model", "gpt-4o-mini",
            "messages", new Object[]{
                Map.of("role", "system", "content", "You are a farming expert."),
                Map.of("role", "user", "content", prompt)
            }
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_URL, entity, Map.class);

        Map choices = (Map) ((java.util.List) response.getBody().get("choices")).get(0);
        Map message = (Map) choices.get("message");
        return (String) message.get("content");
    }
}