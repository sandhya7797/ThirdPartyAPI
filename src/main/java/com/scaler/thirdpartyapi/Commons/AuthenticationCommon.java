package com.scaler.thirdpartyapi.Commons;

import com.scaler.thirdpartyapi.DTOs.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommon {

    private RestTemplate restTemplate;

    public AuthenticationCommon(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<User> validateToken(String token) {

        ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:8181/users/validate/" + token, null, User.class);

        if(response.getBody()==null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(response.getBody());
    }
}
