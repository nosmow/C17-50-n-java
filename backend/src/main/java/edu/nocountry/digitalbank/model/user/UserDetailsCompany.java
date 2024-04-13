package edu.nocountry.digitalbank.model.user;

import edu.nocountry.digitalbank.model.company.Company;

public record UserDetailsCompany(
        int idUser,
        int idCompany,
        String company,
        String cuit,
        String username,
        UserRol role,
        String email,
        String phone,
        String country
) {
    public UserDetailsCompany(User user, Company company) {
        this(
                user.getId(),
                company.getId(),
                company.getCompany(),
                company.getCuit(),
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getPhone(),
                user.getCountry()
        );
    }
}