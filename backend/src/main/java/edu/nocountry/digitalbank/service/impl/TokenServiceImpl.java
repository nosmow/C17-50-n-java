package edu.nocountry.digitalbank.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    private static final int EXPIRATION_HOURS = 2;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Digital Bank")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateDateExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error al generar el token JWT", e);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new IntegrityValidation("No se esta enviando ningun Token");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("Digital Bank")
                    .build()
                    .verify(token);
            verifier.getSubject();

            return verifier.getSubject();
        } catch (JWTVerificationException e) {
            throw new IntegrityValidation("El token no es válido o ha expirado");
        }
    }

    public Instant generateDateExpiration() {
     /*
        ZoneId systemZone = ZoneId.systemDefault();
        LocalDateTime systemDateTime = LocalDateTime.now(systemZone);
        LocalDateTime expirationDateTime = systemDateTime.plusHours(EXPIRATION_HOURS);

        return expirationDateTime.toInstant(ZoneOffset.UTC);
*/
        return LocalDateTime.now(ZoneOffset.UTC).plusHours(2).toInstant(ZoneOffset.ofHours(-3));
    }
}
