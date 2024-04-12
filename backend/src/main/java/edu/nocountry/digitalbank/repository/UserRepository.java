package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    UserDetails findByUsername(String username);
}
