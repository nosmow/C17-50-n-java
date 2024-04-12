package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
