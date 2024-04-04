package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}
