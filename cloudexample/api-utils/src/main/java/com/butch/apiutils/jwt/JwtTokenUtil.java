package com.butch.apiutils.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.butch.apiutils.pojo.User;
import com.butch.apiutils.redis.pojo.RedisUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

@SuppressWarnings("all")
@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;

    private  String secret;

    private Long expiration;

    private String tokenHeader;

    @Autowired
    private JwtProperties jwtProperties;

    @PostConstruct
    private void init(){
        this.secret=jwtProperties.getSecret();
        this.expiration=jwtProperties.getExpiration();
        this.tokenHeader=jwtProperties.getToken();
    }   
    

    private Clock clock = DefaultClock.INSTANCE;

    /**
     * 从request中获取cookie，然后从cookie中获取token
     * @param request
     * @return
     */
    public String getUserIdFromReq(HttpServletRequest request){
    	String token = null;
        for (Cookie cookie : request.getCookies()) {
            if (jwtProperties.getToken().equals(cookie.getName())) {
                if (!"null".equals(cookie.getValue())) {
                    token = cookie.getValue();
                }
            }
        }
        return this.getUserIdFromToken(token);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration);
    }

    public Boolean validateToken(String token, RedisUserDetails userDetails) {
        final String username = getUserIdFromToken(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token)
        );
    }

    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

}