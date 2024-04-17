package com.example.demo.domain.token;

import com.example.demo.domain.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserService userService;

    @Value("${jwt.exp.time}")
    private String TOKEN_EXP_TIME;

    @Value("${jwt.key.secret}")
    private String SECRET_KEY;

    public String createToken(Long userId){
        return Jwts.builder().setSubject(String.valueOf(userId))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(Long.parseLong(TOKEN_EXP_TIME))))
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void getToken(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
        String userId = claims.getSubject();
        userService.findByUserId(Long.valueOf(userId));
    }

}
