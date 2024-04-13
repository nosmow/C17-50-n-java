package edu.nocountry.digitalbank.model.user;

import edu.nocountry.digitalbank.model.person.Person;

import java.time.LocalDate;

public record UserDetailsPerson(
        int idUser,
        int idPerson,
        String name,
        String lastname,
        String dni,
        LocalDate birthdate,
        String username,
        UserRol role,
        String email,
        String phone,
        String country
) {
    public UserDetailsPerson(User user, Person person) {
        this(
                user.getId(),
                person.getId(),
                person.getName(),
                person.getLastname(),
                person.getDni(),
                person.getBirthdate(),
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getPhone(),
                user.getCountry()
        );
    }
}