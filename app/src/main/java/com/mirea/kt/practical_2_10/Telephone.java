package com.mirea.kt.practical_2_10;

public class Telephone {

    private String model;
    private String serialNumber;
    private int Price;

    public Telephone(String model, String serialNumber, int price) {
        this.model = model;
        this.serialNumber = serialNumber;
        Price = price;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getPrice() {
        return Price;
    }
}
