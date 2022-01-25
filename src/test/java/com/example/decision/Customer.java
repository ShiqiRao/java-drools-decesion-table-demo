package com.example.decision;

public class Customer {
    private CustomerType type;

    private int years;

    private int discount;

    public Customer(CustomerType type, int years) {
        this.type = type;
        this.years = years;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    // Standard getters and setters

    public enum CustomerType {
        INDIVIDUAL,
        BUSINESS;
    }
}
