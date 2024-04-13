package edu.nocountry.digitalbank.controller;

import edu.nocountry.digitalbank.model.user.UserDataCompany;
import edu.nocountry.digitalbank.model.user.UserDataPerson;
import edu.nocountry.digitalbank.model.user.UserDetailsCompany;
import edu.nocountry.digitalbank.model.user.UserDetailsPerson;
import edu.nocountry.digitalbank.service.impl.UserServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin()
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserDetailsPerson> saveUserPerson(@RequestBody @Valid UserDataPerson data) {
        var response = userService.saveUserPerson(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/company")
    @Transactional
    public ResponseEntity<UserDetailsCompany> saveUserCompany(@RequestBody @Valid UserDataCompany data) {
        var response = userService.saveUserCompany(data);
        return ResponseEntity.ok(response);
    }
}
