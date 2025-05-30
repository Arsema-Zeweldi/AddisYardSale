package org.web2.addisyardsale.service;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.web2.addisyardsale.dto.PaymentRequest;


@Service
public class PaymentService {
    @Value("${chapa.secret.key}")
    private String chapaSecretKey;

    private final RestTemplate restTemplate;

    public PaymentService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Map<String, Object> initializePayment(PaymentRequest requestData) throws JsonProcessingException {
        String url = "https://api.chapa.co/v1/transaction/initialize";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(chapaSecretKey);

        Map<String, Object> payload = new HashMap<>();
        payload.put("amount", requestData.getAmount());
        payload.put("currency", requestData.getCurrency());
        payload.put("email", requestData.getEmail());
        payload.put("first_name", requestData.getFirstName());
        payload.put("last_name", requestData.getLastName());
        payload.put("phone_number", requestData.getPhoneNumber());
        payload.put("tx_ref", requestData.getTxRef());
        payload.put("callback_url", "https://webhook.site/your-callback");
        payload.put("return_url", "http://localhost:5173/checkout/success");

        Map<String, String> customization = new HashMap<>();
        customization.put("title", "AddisYardSale");
        customization.put("description", "Thank you for shopping with us");
        payload.put("customization", customization);

        Map<String, Object> meta = new HashMap<>();
        meta.put("hide_receipt", "true");
        meta.put("product_ids", requestData.getProducts());
        payload.put("meta", meta);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Chapa payment failed with status: " + response.getStatusCode());
        }

        // Parse JSON response to Map
        return new ObjectMapper().readValue(response.getBody(), Map.class);
    }

}
