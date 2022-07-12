package com.neklyudov.platforma.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String email;
    private String password;

    public User(Long id, String firstName, String lastName, String cardNumber, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

}
