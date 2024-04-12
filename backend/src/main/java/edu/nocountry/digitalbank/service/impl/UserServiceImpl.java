package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.infra.security.SecurityConfigurations;
import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.model.user.UserData;
import edu.nocountry.digitalbank.model.user.UserDetails;
import edu.nocountry.digitalbank.repository.UserRepository;
import edu.nocountry.digitalbank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserDetails saveUser(UserData data) {
        validateUsername(data.username());
        validateEmail(data.email());

        var user = User.builder()
                .username(data.username())
                .role(data.role())
                .email(data.email())
                .phone(data.phone())
                .country(data.country())
                .password(SecurityConfigurations.encryptPassword(data.password()))
                .active(true)
                .build();

        userRepository.save(user);
        return new UserDetails(user);
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