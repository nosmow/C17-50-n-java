package edu.nocountry.digitalbank.repository;

import edu.nocountry.digitalbank.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
