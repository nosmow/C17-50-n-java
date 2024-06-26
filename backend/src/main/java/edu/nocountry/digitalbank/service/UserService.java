package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.model.user.UserDataPerson;
import edu.nocountry.digitalbank.model.user.UserDetailsPerson;

public interface UserService {
    UserDetailsPerson saveUserPerson(UserDataPerson data);

    User findUserUsername(String username);
}
