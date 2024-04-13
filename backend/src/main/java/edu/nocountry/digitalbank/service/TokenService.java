package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.user.User;

import java.time.Instant;

public interface TokenService {
    String generateToken(User user);

    String getSubject(String token);

    Instant generateDateExpiration();
}
