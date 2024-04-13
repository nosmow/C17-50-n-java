package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.person.Person;
import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.model.user.UserDataPerson;

public interface PersonService {

    Person savePerson(UserDataPerson data, User user);
}
