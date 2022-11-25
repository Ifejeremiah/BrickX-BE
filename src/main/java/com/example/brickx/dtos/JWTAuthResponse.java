package com.example.brickx.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTAuthResponse {
    private String accessToken;

    private Long id;



    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getId() {
        return id;
    }

}
