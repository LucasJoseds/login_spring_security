package com.lucas.projeto_login.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.lucas.projeto_login.Model.Role;
import com.lucas.projeto_login.Model.User;

@Service
public class TokenService {


    private final JwtEncoder encoder;
    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String createToken(User user){
        
        String scopes = user.getRoles().stream().map(Role:: getName).collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("security")
        .subject(user.getId().toString())
        .issuedAt(Instant.now())
        .claim("scope", scopes)
        .expiresAt(Instant.now().plusSeconds(300L))
        .build();
        
        Jwt jwt = encoder.encode(JwtEncoderParameters.from(claims));
        String jwtValue = jwt.getTokenValue();
        return jwtValue;

    }

}
