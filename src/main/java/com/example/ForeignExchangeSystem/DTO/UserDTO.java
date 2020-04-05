package com.example.ForeignExchangeSystem.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    @Email(message = "incorrect email address")
    private String email;
    @Size(min= 8, max=16, message = "Password must be grater than 8 char or less then 16 char")
    @NotNull
    @NotEmpty
    private String password;
    private String matching_password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatching_password() {
        return matching_password;
    }

    public void setMatching_password(String matching_password) {
        this.matching_password = matching_password;
    }
}
