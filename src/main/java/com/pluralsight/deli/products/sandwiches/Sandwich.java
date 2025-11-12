package com.pluralsight.deli.products.sandwiches;

import com.pluralsight.deli.common.enums.BreadType;
import com.pluralsight.deli.common.enums.SandwichSize;
import com.pluralsight.deli.products.Product;
import com.pluralsight.deli.products.sandwiches.toppings.Topping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Sandwich extends Product {
    private SandwichSize sandwichSize;
    private BreadType bread;
    private final List<Topping> sandwichToppings;
    private boolean isToasted;


    public Sandwich(String productName, SandwichSize sandwichSize, BreadType bread, List<Topping> sandwichToppings, boolean isToasted) {
        super(productName);
        this.sandwichSize = sandwichSize;
        this.bread = bread;
        this.sandwichToppings = new ArrayList<>(sandwichToppings == null ? List.of() : sandwichToppings);
        this.isToasted = isToasted;
    }

    public Sandwich(String productName, SandwichSize sandwichSize, BreadType bread, boolean isToasted) {
        this(productName, sandwichSize, bread, new ArrayList<>(), isToasted);
    }

    public List<Topping> getSandwichToppings() {
        return Collections.unmodifiableList(sandwichToppings);
    }

//    public void setSandwichToppings(List<Topping> sandwichToppings) {
//        this.sandwichToppings = sandwichToppings;
//    }

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
        if (t != null) sandwichToppings.add(t);
    }

    public void removeTopping(Topping t){
        sandwichToppings.remove(t);
    }

    @Override
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

    public String printDisplay(){
        String sandwich = sandwichToppings.isEmpty() ? "(\uD83D\uDEAB no toppings)" : sandwichToppings.stream()
                .map(Object::toString).collect(Collectors.joining(", "));

        return String.format("""
            🥪 %s (%s, %s)
            🔥 Toasted: %s
            🍞 Bread: %s
            🧂 Toppings: %s
            💵 Total: $%.2f
            """,
                getProductName(), sandwichSize, bread, isToasted ? "Yes" : "No",
                sandwich, getCost());
    }

    @Override
    public String toString() {
        return printDisplay();
    }
}
