package com.blogapplication.api.payloads;

public class JWTAuthResponse {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public JWTAuthResponse() {
    }
}
