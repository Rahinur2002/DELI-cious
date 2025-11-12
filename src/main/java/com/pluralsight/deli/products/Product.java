package com.pluralsight.deli.products;

public abstract class Product {
    private String productName;

    public Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public abstract double getCost();


}
