package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.person.Person;
import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.model.user.UserDataPerson;
import edu.nocountry.digitalbank.repository.PersonRepository;
import edu.nocountry.digitalbank.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public Person savePerson(UserDataPerson data, User user) {
        validateDni(data.dni());

        var person = Person.builder()
                .user(user)
                .name(data.name())
                .lastname(data.lastname())
                .dni(data.dni())
                .build();


        personRepository.save(person);

        return person;
    }

    private void validateDni(String dni) {
        if (dni.length() < 8 || dni.length() > 9) {
            throw new IntegrityValidation("El DNI ingresa no tiene el formato correcto");
        }
    }
}
