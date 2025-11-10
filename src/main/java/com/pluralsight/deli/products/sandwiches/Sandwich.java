package com.pluralsight.deli.products.sandwiches;

import com.pluralsight.deli.common.enums.BreadType;
import com.pluralsight.deli.common.enums.SandwichSize;
import com.pluralsight.deli.products.sandwiches.toppings.Topping;

import java.util.ArrayList;

public class Sandwich {
    private ArrayList<Topping> sandwichToppings;
    private boolean isToasted;
    private BreadType bread;
    private SandwichSize sandwichSize;

    public Sandwich(ArrayList<Topping> sandwichToppings, boolean isToasted, BreadType bread, SandwichSize sandwichSize) {
        this.sandwichToppings = sandwichToppings;
        this.isToasted = isToasted;
        this.bread = bread;
        this.sandwichSize = sandwichSize;
    }

    public double getCost(){
        return 0;
    }

    public void addTopping(Topping t){

    }

    public void removeTopping(Topping t){

    }

    public void printDisplay(){

    }
}
