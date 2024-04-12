package edu.nocountry.digitalbank.controller;

import edu.nocountry.digitalbank.model.user.UserData;
import edu.nocountry.digitalbank.model.user.UserDetails;
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
    public ResponseEntity<UserDetails> saveUser(@RequestBody @Valid UserData data) {
        var response = userService.saveUser(data);
        return ResponseEntity.ok(response);
    }
}
