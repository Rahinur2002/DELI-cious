package com.pluralsight.deli.products.sandwiches.toppings.premium;

import com.pluralsight.deli.common.enums.SandwichSize;

public class Cheese extends PremiumTopping{
    public Cheese(String name, boolean hasExtra) {
        super(name, hasExtra);
    }

    @Override
    public double basePrice(SandwichSize s){
        switch (s) {
            case FOUR -> {
                return 0.75;
            }
            case EIGHT -> {
                return 1.50;
            }
            case TWELVE -> {
                return 2.25;
            }
            default -> {
                return 0;
            }
        }
    }
}
