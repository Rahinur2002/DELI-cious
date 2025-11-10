package com.pluralsight.deli.orders;

import com.pluralsight.deli.products.Product;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> products;

    public Order(ArrayList<Product> products) {
        this.products = products;
    }


}
