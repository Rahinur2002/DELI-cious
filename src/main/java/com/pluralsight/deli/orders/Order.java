package com.pluralsight.deli.orders;

import com.pluralsight.deli.products.Product;
import com.pluralsight.deli.products.chips.Chip;
import com.pluralsight.deli.products.drinks.Drink;
import com.pluralsight.deli.products.sandwiches.Sandwich;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Product> products = new ArrayList<>();

    public Order() {}

    public Order(List<Product> product) {
        if(product != null) this.products.addAll(product);
    }
    public void addProduct(Product p) {
            if (p != null) products.add(p);
    }

    public void addSandwich(Sandwich s){
        addProduct(s);
    }

    public void addDrink(Drink d){
        addProduct(d);
    }

    public void addChip(Chip c){
        addProduct(c);
    }

    public double getTotal(){
        return products.stream().mapToDouble(Product::getCost).sum();
    }

    public void cancelOrder(){
        products.clear();
    }

    public List<Product> getProducts() { return new ArrayList<>(products); }

    public String printDisplay(){
        StringBuilder sb = new StringBuilder();
        sb.append("=== \uD83E\uDDFE ORDER SUMMARY ===\n");
        if (products.isEmpty()) {
            sb.append("\uD83D\uDEAB no items right now\n");
        } else {
            for(int i = 0; i < products.size(); i++){
                sb.append(String.format("%2d) %s%n", i + 1, products.get(i)));
            }
        }
        sb.append(String.format("TOTAL: $%.2f%n", getTotal()));
        return sb.toString();
    }
}
