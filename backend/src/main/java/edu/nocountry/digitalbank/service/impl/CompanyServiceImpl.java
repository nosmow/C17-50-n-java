package edu.nocountry.digitalbank.service.impl;

import edu.nocountry.digitalbank.infra.errors.IntegrityValidation;
import edu.nocountry.digitalbank.model.company.Company;
import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.model.user.UserDataCompany;
import edu.nocountry.digitalbank.repository.CompanyRepository;
import edu.nocountry.digitalbank.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    
    public Company saveCompany(UserDataCompany data, User user) {
        validateCuit(data.cuit());

        var company = Company.builder()
                .user(user)
                .company(data.company())
                .cuit(data.cuit())
                .build();

        companyRepository.save(company);

        return company;
    }

    private void validateCuit(String cuit) {
        if (cuit.length() < 11) {
            throw new IntegrityValidation("El CUIT ingresa no tiene el formato correcto");
        }
    }
}
