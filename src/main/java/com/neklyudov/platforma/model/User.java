package com.neklyudov.platforma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String email;
    private String password;
    private Role role;

    public User(Long id, String firstName,
                String lastName, String cardNumber,
                String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

}
