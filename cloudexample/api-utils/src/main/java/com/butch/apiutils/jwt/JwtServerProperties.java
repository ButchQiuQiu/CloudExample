package com.butch.apiutils.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtServerProperties {
    /**
     * 密钥
     */
    private String secret;
    /**
     * 过期时间
     */
    private Long expiration;
    /**
     * token头名
     */
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
    @Override
	public String toString() {
		return "JwtServerProperties [secret=" + secret + ", expiration=" + expiration + ", token=" + token
				+ ", hashCode()=" + hashCode() + "]";
	}
}