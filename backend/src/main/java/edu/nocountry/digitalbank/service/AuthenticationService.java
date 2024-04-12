package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.jwt.DataJWToken;
import edu.nocountry.digitalbank.model.user.UserDataAuthentication;

public interface AuthenticationService {
    public DataJWToken authenticationUser(UserDataAuthentication data);
}
