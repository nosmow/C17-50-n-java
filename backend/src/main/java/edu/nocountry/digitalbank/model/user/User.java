package edu.nocountry.digitalbank.model.user;

import edu.nocountry.digitalbank.model.account.Account;
import edu.nocountry.digitalbank.model.company.Company;
import edu.nocountry.digitalbank.model.person.Person;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Enumerated(EnumType.STRING)
    private UserRol role;

    private String email;

    private String phone;

    private String country;

    private String password;

    private boolean active;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Person person;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Company company;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Account account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
