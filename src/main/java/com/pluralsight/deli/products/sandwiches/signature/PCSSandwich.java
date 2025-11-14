package com.pluralsight.deli.products.sandwiches.signature;

import com.pluralsight.deli.common.enums.BreadType;
import com.pluralsight.deli.common.enums.SandwichSize;
import com.pluralsight.deli.products.sandwiches.Sandwich;
import com.pluralsight.deli.products.sandwiches.toppings.RegularTopping;
import com.pluralsight.deli.products.sandwiches.toppings.Sauce;
import com.pluralsight.deli.products.sandwiches.toppings.Topping;
import com.pluralsight.deli.products.sandwiches.toppings.premium.Cheese;
import com.pluralsight.deli.products.sandwiches.toppings.premium.Meat;

public class PCSSandwich extends Sandwich {
    public PCSSandwich() {
        super("Philly Cheese Steak Sandwich", SandwichSize.EIGHT, BreadType.WHITE, true);

        addTopping(new Meat("Steak", false));
        addTopping(new Cheese("American Cheese", false));
        addTopping(new RegularTopping("Peppers"));
        addTopping(new Sauce("Mayo"));
    }
}
