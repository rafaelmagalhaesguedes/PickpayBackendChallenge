package com.pickpaysimplificado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthorizationService {

    private final RestTemplate restTemplate;

    private static final String AUTHORIZATION_URL = "https://util.devi.tools/api/v2/authorize";

    @Autowired
    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean authorizeTransaction() {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(AUTHORIZATION_URL, Map.class);

        return Optional.of(authorizationResponse)
                .filter(response -> response.getStatusCode() == HttpStatus.OK)
                .map(ResponseEntity::getBody)
                .filter(body -> "success".equalsIgnoreCase((String) body.get("status")))
                .map(body -> (Map<String, Object>) body.get("data"))
                .map(data -> (Boolean) data.get("authorization"))
                .orElse(false);
    }
}
