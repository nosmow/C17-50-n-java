package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.user.UserData;
import edu.nocountry.digitalbank.model.user.UserDetails;

public interface UserService  {
    public UserDetails save(UserData data);
}
