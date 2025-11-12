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
}
