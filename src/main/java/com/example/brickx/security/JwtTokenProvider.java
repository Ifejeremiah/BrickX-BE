package com.example.brickx.security;


import com.example.brickx.entities.User;
import com.example.brickx.exceptions.BrickxAPIException;
import com.example.brickx.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Autowired
    private UserRepository userRepository;

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    // generate token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findUserByEmail(username);
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(username)
                .claim("email", user.getEmail())
                .claim("role", user.getRole().toString())
                .claim("user_id", user.getId().toString())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // get username from the token
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new BrickxAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new BrickxAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new BrickxAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new BrickxAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new BrickxAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }

}
