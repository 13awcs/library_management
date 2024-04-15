package com.library.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.library.auth.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {

    @Value("${key.jwtsecret}")
    private String keyJwtSecret;

    @Value("${key.jwtissuer}")
    private String keyJwtIssuer;


    @PostConstruct
    void print(){
        System.err.println(keyJwtSecret+keyJwtIssuer);
    }

    public String generateAccessToken(User user) {
        Algorithm algorithm = Algorithm.HMAC512(keyJwtSecret.getBytes());

        List<String> authorities = new ArrayList<>();
        authorities.add(user.getRole());


        return JWT.create()
                .withSubject(String.format("%s,%s,%s", user.getId(), user.getUsername(), user.getEmployeeId()))
                .withIssuer(keyJwtIssuer)
                .withClaim("roles", authorities)
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 100000))
                .sign(algorithm);
    }

    public String generateRefreshToken(User user) {
        Algorithm algorithm = Algorithm.HMAC512(keyJwtSecret.getBytes());

        return JWT.create()
                .withSubject(String.format("%s,%s,%s,%s", user.getId(), user.getUsername(), user.getEmployeeId(), user.getStudentId()))
                .withIssuer(keyJwtIssuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(algorithm);
    }

    public boolean validate(String token) {
        try {
            Long expiresAt = JWT.decode(token).getExpiresAt().getTime();
            Calendar cal = Calendar.getInstance();
            if (expiresAt >= cal.getTime().getTime()) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("JWT is invalid - {%s}", e.getMessage()));
        }

        return false;
    }

    public String getUserName(String token) {
        String tokenAfter = token.replace("Bearer ", "");
        String subject = JWT.decode(tokenAfter).getSubject();

        return subject.split(",")[1];
    }

    public Long getUserId(String token) {
        String tokenAfter = token.replace("Bearer ", "");
        String subject = JWT.decode(tokenAfter).getSubject();
        return Long.valueOf(subject.split(",")[0]);
    }

    public Long getEmployeeId(String token) {
        String tokenAfter = token.replace("Bearer ", "");
        String subject = JWT.decode(tokenAfter).getSubject();
        return Long.valueOf(subject.split(",")[2]);
    }

    public Long getStudentId(String token) {
        String tokenAfter = token.replace("Bearer ", "");
        String subject = JWT.decode(tokenAfter).getSubject();
        return Long.valueOf(subject.split(",")[3]);
    }

}
