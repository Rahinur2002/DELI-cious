package com.pluralsight.deli.products.sandwiches;

import com.pluralsight.deli.common.enums.BreadType;
import com.pluralsight.deli.common.enums.SandwichSize;
import com.pluralsight.deli.products.Product;
import com.pluralsight.deli.products.sandwiches.toppings.Topping;

import java.util.ArrayList;

public class Sandwich extends Product {
    private SandwichSize sandwichSize;
    private BreadType bread;
    private ArrayList<Topping> sandwichToppings;
    private boolean isToasted;

    public Sandwich(String productName, SandwichSize sandwichSize, BreadType bread, ArrayList<Topping> sandwichToppings, boolean isToasted) {
        super(productName);
        this.sandwichSize = sandwichSize;
        this.bread = bread;
        this.sandwichToppings = new ArrayList<>();
        this.isToasted = isToasted;
    }

    public ArrayList<Topping> getSandwichToppings() {
        return sandwichToppings;
    }

    public void setSandwichToppings(ArrayList<Topping> sandwichToppings) {
        this.sandwichToppings = sandwichToppings;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public BreadType getBread() {
        return bread;
    }

    public void setBread(BreadType bread) {
        this.bread = bread;
    }

    public SandwichSize getSandwichSize() {
        return sandwichSize;
    }

    public void setSandwichSize(SandwichSize sandwichSize) {
        this.sandwichSize = sandwichSize;
    }


    public void addTopping(Topping t){
        sandwichToppings.add(t);
    }

    public void removeTopping(Topping t){
        sandwichToppings.remove(t);
    }

    public double getCost(){
        double originalPrice = switch (sandwichSize) {
            case FOUR -> 5.50;
            case EIGHT -> 7.00;
            case TWELVE -> 8.50;
        };
        double toppingCost = sandwichToppings.stream().
                mapToDouble(t -> t.getPrice(sandwichSize)).sum();

        return originalPrice + toppingCost;
    }

    public void printDisplay(){

    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "sandwichToppings=" + sandwichToppings +
                ", isToasted=" + isToasted +
                ", bread=" + bread +
                ", sandwichSize=" + sandwichSize +
                '}';
    }
}
