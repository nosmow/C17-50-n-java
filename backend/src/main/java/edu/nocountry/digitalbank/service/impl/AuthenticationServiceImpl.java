package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.jwt.DataJWToken;
import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.model.user.UserDataAuthentication;
import edu.nocountry.digitalbank.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final TokenServiceImpl tokenService;

    public DataJWToken authenticationUser(UserDataAuthentication data) {
        try {
            Authentication autoToken = new UsernamePasswordAuthenticationToken(data.username(),
                    data.password());
            var authenticatedUser = authenticationManager.authenticate(autoToken);
            var JWT = tokenService.generateToken((User) authenticatedUser.getPrincipal());

            return new DataJWToken(JWT);
        } catch (Exception e) {
            throw new IntegrityValidation("Usuario o contraseña incorrecto");
        }
    }
}
