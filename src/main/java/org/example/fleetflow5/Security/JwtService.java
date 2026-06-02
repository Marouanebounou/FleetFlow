package org.example.fleetflow5.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.fleetflow5.Models.User;
import org.example.fleetflow5.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;
    @Autowired
    private UserRepository userRepository;

    private SecretKey getSigninSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email){
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new IllegalArgumentException("User not found");
        }
        return Jwts.builder()
                .issuedAt(new Date())
                .subject(email)
                .claim("role", user.getRole())
                .expiration(new Date(new Date().getTime() + expiration))
                .signWith(getSigninSecretKey())
                .compact();
    }

    public String extractUserEmail(String token){
        return Jwts.parser()
                .setSigningKey(getSigninSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserEmail(token);
        return username.equals(userDetails.getUsername());
    }

}
