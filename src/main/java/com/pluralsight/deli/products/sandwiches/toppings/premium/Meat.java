package com.pluralsight.deli.products.sandwiches.toppings.premium;

import com.pluralsight.deli.common.enums.SandwichSize;

public class Meat extends PremiumTopping{
    public Meat(String name, boolean hasExtra) {
        super(name, hasExtra);
    }

    @Override
    public double basePrice(SandwichSize s) {
        switch (s) {
            case FOUR -> {
                return 1.00;
            }
            case EIGHT -> {
                return 2.00;
            }
            case TWELVE -> {
                return 3.00;
            }
            default -> {
                return 0;
            }
        }
    }
}
