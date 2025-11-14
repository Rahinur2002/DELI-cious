package com.pluralsight.deli.products.sandwiches.toppings;

import com.pluralsight.deli.common.enums.SandwichSize;

public class Side extends Topping{
    public Side(String name) {
        super(name);
    }

    public double getPrice(SandwichSize s){
        return 0.0;
    }

}
