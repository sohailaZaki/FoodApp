package com.example.nnnn;

public class Food {
    private int id;
    private String name;
    private String price;
    private byte[] image;



    public Food(int id, String name, String price, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public byte[] getImage() { return image; }
}
