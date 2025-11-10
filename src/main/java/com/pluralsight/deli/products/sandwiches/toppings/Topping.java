package com.pluralsight.deli.products.sandwiches.toppings;

import com.pluralsight.deli.common.enums.SandwichSize;

public abstract class Topping {
    private String name;

    public Topping(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract double getPrice(SandwichSize s);

    @Override
    public String toString() {
        return name;
    }
}
