package edu.nocountry.digitalbank.controller;

import edu.nocountry.digitalbank.model.user.UserData;
import edu.nocountry.digitalbank.model.user.UserDetails;
import edu.nocountry.digitalbank.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDetails> save(@RequestBody @Valid UserData data) {
        var response = userService.save(data);
        return ResponseEntity.ok(response);
    }
}
