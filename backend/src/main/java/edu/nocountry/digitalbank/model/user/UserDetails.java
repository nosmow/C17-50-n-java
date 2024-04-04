package edu.nocountry.digitalbank.model.user;

public record UserDetails(
        int id,
        UserRol role,
        String email,
        int phone,
        String country,
        String password
) {
    public UserDetails(User user) {
        this(
                user.getId(),
                user.getRole(),
                user.getEmail(),
                user.getPhone(),
                user.getCountry(),
                user.getPassword()
        );
    }
}
