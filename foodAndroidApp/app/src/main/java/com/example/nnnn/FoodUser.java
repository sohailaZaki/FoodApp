package com.example.nnnn;

public class FoodUser {
    private String name;
    private String price;
    private byte[] image;

    public FoodUser(String name, String price, byte[] image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }
}
