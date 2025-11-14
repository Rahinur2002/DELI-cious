package com.pluralsight.deli.products.chips;

import com.pluralsight.deli.products.Product;

public class Chip extends Product {
    public Chip(String productName) {
        super(productName);
    }

    @Override
    public double getCost() {
        return 1.50;
    }
    public String printDisplay() {
        return String.format("\uD83C\uDF5F Chips: %s - $%.2f", getProductName(), getCost());
    }
    public String toString() {
        return printDisplay();
    }
}
