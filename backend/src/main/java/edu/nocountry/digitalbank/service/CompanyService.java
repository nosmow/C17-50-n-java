package edu.nocountry.digitalbank.service;

import edu.nocountry.digitalbank.model.company.Company;
import edu.nocountry.digitalbank.model.user.User;
import edu.nocountry.digitalbank.model.user.UserDataCompany;

public interface CompanyService {

    Company saveCompany(UserDataCompany data, User user);
}
