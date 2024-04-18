package edu.nocountry.digitalbank.util;

import edu.nocountry.digitalbank.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtUtils {

    private final TokenService tokenService;

    public String extractUsername(String token) {
        String jwToken = token.replace("Bearer ", "");
        return tokenService.getSubject(jwToken);
    }
}
