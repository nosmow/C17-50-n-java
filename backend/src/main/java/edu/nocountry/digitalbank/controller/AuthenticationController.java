package edu.nocountry.digitalbank.controller;

import edu.nocountry.digitalbank.model.jwt.DataJWToken;
import edu.nocountry.digitalbank.model.user.UserDataAuthentication;
import edu.nocountry.digitalbank.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping
    public ResponseEntity<DataJWToken> authenticationUser(@RequestBody @Valid UserDataAuthentication data) {
        return ResponseEntity.ok(authenticationService.authenticationUser(data));
    }
}
