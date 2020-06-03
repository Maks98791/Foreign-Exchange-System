package com.example.ForeignExchangeSystem.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;


@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="value")
    private double value;

    @Column(name="waluta")
    private String waluta;

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

}
