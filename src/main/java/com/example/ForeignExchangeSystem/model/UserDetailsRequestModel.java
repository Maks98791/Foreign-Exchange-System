package com.example.ForeignExchangeSystem.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {

    private int id;
    @NotNull(message = "email can't be empty")
    @Email(message = "Incorrect email")
    private String email;
    @NotNull(message = "Password can't be empty")
    @Size(min= 8, max=16, message = "Password must be grater than 8 char or less then 16 char")
    private String password;
    private double money;

    public UserDetailsRequestModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
