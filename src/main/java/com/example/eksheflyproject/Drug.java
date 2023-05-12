package com.example.eksheflyproject;

import java.time.LocalDate;
import java.util.Locale;

public class Drug {
    private String name;
    private LocalDate expireDate;
    private float price;
    public Drug(String name){
        this.name = name.toUpperCase(Locale.ROOT);
        this.expireDate=LocalDate.of(2023,12,12);
        this.price=100;
    }
    public Drug(String name, LocalDate expireDate, float price) {
        this.name = name.toUpperCase(Locale.ROOT);
        this.expireDate = expireDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}