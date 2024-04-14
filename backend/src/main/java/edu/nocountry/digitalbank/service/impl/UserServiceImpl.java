package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.infra.security.SecurityConfigurations;
import edu.nocountry.digitalbank.model.user.*;
import edu.nocountry.digitalbank.repository.UserRepository;
import edu.nocountry.digitalbank.service.AccountService;
import edu.nocountry.digitalbank.service.CompanyService;
import edu.nocountry.digitalbank.service.PersonService;
import edu.nocountry.digitalbank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonService personService;
    private final CompanyService companyService;
    private final AccountService accountService;

    public UserDetailsPerson saveUserPerson(UserDataPerson data) {
        validateUsername(data.username());
        validateEmail(data.email());

        var user = User.builder()
                .username(data.username())
                .role(data.role())
                .email(data.email())
                .phone(data.phone())
                .country(ZoneId.systemDefault().toString())
                .password(SecurityConfigurations.encryptPassword(data.password()))
                .active(true)
                .build();

        userRepository.save(user);
        var person = personService.savePerson(data, user);
        accountService.saveAccount(user);

        return new UserDetailsPerson(user, person);
    }

    public UserDetailsCompany saveUserCompany(UserDataCompany data) {
        validateUsername(data.username());
        validateEmail(data.email());

        var user = User.builder()
                .username(data.username())
                .role(data.role())
                .email(data.email())
                .phone(data.phone())
                .country(ZoneId.systemDefault().toString())
                .password(SecurityConfigurations.encryptPassword(data.password()))
                .active(true)
                .build();

        userRepository.save(user);
        var company = companyService.saveCompany(data, user);
        accountService.saveAccount(user);

        return new UserDetailsCompany(user, company);
    }

    public void validateUsername(String username) {
        if (username != null && userRepository.existsByUsername(username)) {
            throw new IntegrityValidation("Este nombre de usuario no esta disponible");
        }
    }

    public void validateEmail(String email) {
        if (email != null && userRepository.existsByEmail(email)) {
            throw new IntegrityValidation("Este correo ya se encuentra registrado");
        }
    }
}