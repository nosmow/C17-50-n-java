package edu.nocountry.digitalbank.model.user;

import edu.nocountry.digitalbank.model.person.Person;

public record UserDetailsPerson(
        int idUser,
        int idPerson,
        String name,
        String lastname,
        String dni,
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
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getPhone(),
                user.getCountry()
        );
    }
}