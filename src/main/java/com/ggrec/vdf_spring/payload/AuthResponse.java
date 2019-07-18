package com.ggrec.vdf_spring.payload;

import com.ggrec.vdf_spring.config.SecurityConfig;

public class AuthResponse {

    private String accessToken;
    private String tokenType = SecurityConfig.AUTH_SCHEME;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}