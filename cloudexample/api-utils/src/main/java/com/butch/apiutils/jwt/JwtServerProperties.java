package com.butch.apiutils.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtServerProperties {
    private String secret;
    private Long expiration;
    private String token;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}