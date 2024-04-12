package edu.nocountry.digitalbank.model.person;

import edu.nocountry.digitalbank.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Table(name = "people")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String lastname;
    
    private String dni;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
}
