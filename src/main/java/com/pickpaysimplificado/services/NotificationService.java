package com.pickpaysimplificado.services;

import com.pickpaysimplificado.dto.NotificationDTO;
import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.exceptions.FailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationService {
    private static final String NOTIFICATION_URL = "https://util.devi.tools/api/v1/notify";
    private static final int MAX_RETRIES = 3;
    private final RestTemplate restTemplate;

    @Autowired
    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotification(User user, String message) throws FailedException {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        int attempts = 0;
        boolean success = false;

        while (attempts < MAX_RETRIES && !success) {
            attempts++;
            ResponseEntity<Map> response = restTemplate
                    .postForEntity(NOTIFICATION_URL, notificationRequest, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> responseBody = response.getBody();
                if (responseBody != null && "success".equalsIgnoreCase((String) responseBody.get("status"))) {
                    success = true;
                }
            }
        }

        if (!success) {
            throw new FailedException("Failed to send notification after " + MAX_RETRIES + " attempts.");
        }
    }
}