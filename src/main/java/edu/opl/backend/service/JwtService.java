package edu.opl.backend.service;

import edu.opl.backend.dto.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;
    @Value("${spring.security.oauth2.resourceserver.jwt.secret}")
    private String secretKey;

    public String generateToken(Person person) {
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .subject(person.getUsername())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600 * 24))
                .claim(person.getFullName(), person.getRole())
                .build();
        JwtEncoderParameters parameters = JwtEncoderParameters
                .from(JwsHeader.with(MacAlgorithm.HS256).build(), claimsSet);
        return jwtEncoder.encode(parameters).getTokenValue();
    }
}
