package edu.nocountry.digitalbank.model.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @Enumerated(EnumType.STRING)
    private UserRol role;
    private String email;
    private String phone;
    private String country;
    private String password;
    private boolean active;
}