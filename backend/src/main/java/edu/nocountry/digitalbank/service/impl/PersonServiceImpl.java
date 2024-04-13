package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.person.Person;
import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.model.user.UserDataPerson;
import edu.nocountry.digitalbank.repository.PersonRepository;
import edu.nocountry.digitalbank.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public Person savePerson(UserDataPerson data, User user) {
        validateDni(data.dni());
        validateBirthdate(data.birthdate());

        var person = Person.builder()
                .user(user)
                .name(data.name())
                .lastname(data.lastname())
                .dni(data.dni())
                .birthdate(data.birthdate())
                .build();


        personRepository.save(person);

        return person;
    }

    private void validateDni(String dni) {
        if (dni.length() < 8 || dni.length() > 9) {
            throw new IntegrityValidation("El DNI ingresa no tiene el formato correcto");
        }
    }

    private void validateBirthdate(LocalDate date) {
        if (Period.between(date, LocalDate.now()).getYears() < 18) {
            throw new IntegrityValidation("Debes ser mayor de edad para registrarte");
        }
    }
}
