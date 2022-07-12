package com.neklyudov.platforma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String cardNumber;
    public String password;
}
