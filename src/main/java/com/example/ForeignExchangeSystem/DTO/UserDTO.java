package com.example.ForeignExchangeSystem.DTO;

public class UserDTO {

    int id;
    String email;
    String password;
    double money; //na razie zamiast klasy wallet żeby było łatwiej ogarnąć :*

    public UserDTO(int id, String email, String password, double money) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.money = money;
    }

    public UserDTO() {
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
