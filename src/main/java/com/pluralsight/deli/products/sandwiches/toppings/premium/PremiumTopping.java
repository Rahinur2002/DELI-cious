package com.pluralsight.deli.products.sandwiches.toppings.premium;

import com.pluralsight.deli.common.enums.SandwichSize;
import com.pluralsight.deli.products.sandwiches.Sandwich;
import com.pluralsight.deli.products.sandwiches.toppings.Topping;

public abstract class PremiumTopping extends Topping {
    private boolean hasExtra;

    public PremiumTopping(String name) {
        super(name);
        this.hasExtra = false;
    }

    public PremiumTopping(String name, boolean hasExtra) {
        super(name);
        this.hasExtra = hasExtra;
    }

    public boolean hasExtra(){
        return this.hasExtra;
    }

    public void setHasExtra(boolean hasExtra) {
        this.hasExtra = hasExtra;
    }

    public abstract double basePrice(SandwichSize s);

    @Override
    public double getPrice(SandwichSize s){
        double originalPrice  = basePrice(s);

        return hasExtra() ? originalPrice * 2.0 : originalPrice;
    }

}
