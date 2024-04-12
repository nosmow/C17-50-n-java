package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.user.User;

import java.time.Instant;

public interface TokenService {
    public String generateToken(User user);
    public String getSubject(String token);
    public Instant generateDateExpiration();
}
