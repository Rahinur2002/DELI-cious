package com.pluralsight.deli.products.drinks;

import com.pluralsight.deli.common.enums.DrinkSize;
import com.pluralsight.deli.products.Product;

public class Drink extends Product {
    private final DrinkSize size;

    public Drink(String productName, DrinkSize size) {
        super(productName);
        this.size = size;
    }

    @Override
    public double getCost() {
       return switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
        };
    }

    public String printDisplay() {
        return String.format("\uD83E\uDD64 Drinks: %s (%s) - $%.2f", getProductName(), size, getCost());
    }

    @Override
    public String toString() {
        return printDisplay();
    }
}
