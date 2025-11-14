package com.pluralsight.deli.products.sandwiches.signature;

import com.pluralsight.deli.common.enums.BreadType;
import com.pluralsight.deli.common.enums.SandwichSize;
import com.pluralsight.deli.products.sandwiches.Sandwich;
import com.pluralsight.deli.products.sandwiches.toppings.RegularTopping;
import com.pluralsight.deli.products.sandwiches.toppings.Sauce;
import com.pluralsight.deli.products.sandwiches.toppings.Topping;
import com.pluralsight.deli.products.sandwiches.toppings.premium.Cheese;
import com.pluralsight.deli.products.sandwiches.toppings.premium.Meat;

public class BLTSandwich extends Sandwich {
    public BLTSandwich() {
        super("BLT Sandwich", SandwichSize.EIGHT, BreadType.WHITE, true);

    addTopping(new Meat("Bacon", false));
    addTopping(new Cheese("Cheddar", false));
    addTopping(new RegularTopping("Lettuce"));
    addTopping(new RegularTopping("Tomato"));
    addTopping(new Sauce("Ranch"));
    }
}
