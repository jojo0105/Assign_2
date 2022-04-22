package com.example.assignment_2;

public class Product {
    private String name;
    private String description;
    private double price;
    private String imageFile;

    public Product(String name, String description, double price, String imageFile) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageFile = imageFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
