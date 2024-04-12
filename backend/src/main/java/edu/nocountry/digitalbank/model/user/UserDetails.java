package edu.nocountry.digitalbank.model.user;

public record UserDetails(
        int id,
        String username,
        UserRol role,
        String email,
        String phone,
        String country
) {
    public UserDetails(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getPhone(),
                user.getCountry()
        );
    }
}