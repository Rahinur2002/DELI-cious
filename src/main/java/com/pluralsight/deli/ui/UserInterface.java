package com.pluralsight.deli.ui;

import com.pluralsight.deli.common.enums.BreadType;
import com.pluralsight.deli.common.enums.SandwichSize;
import com.pluralsight.deli.orders.Order;
import com.pluralsight.deli.products.sandwiches.Sandwich;
import com.pluralsight.deli.products.sandwiches.toppings.Topping;
import com.pluralsight.deli.products.sandwiches.toppings.premium.Cheese;
import com.pluralsight.deli.products.sandwiches.toppings.premium.Meat;

import java.util.Calendar;
import java.util.Scanner;

import static com.pluralsight.deli.common.Utility.Utility.printHeader;

public class UserInterface {

    private final Scanner scanner;
    private Order currentOrder;

    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void display() {
        boolean quit = false;
        while (!quit) {
            System.out.println("---------- Menu ----------");
            System.out.println("1. New Order.");
            System.out.println("0. Exit");

            System.out.print("\uD83D\uDC49 Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    currentOrder = new Order();
                    orderScreen();
                    break;
                case "0":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Thank you! come again.");
    }

    public void orderScreen() {
        boolean quit = false;
        while (!quit) {
            System.out.println("---------- Menu ----------");
            System.out.println("1. Add Sandwich.");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Chips");
            System.out.println("4. Checkout");
            System.out.println("0. Cancel Order & go back home page");

            System.out.print("\uD83D\uDC49 Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addSandwich();
                    break;
                case "2":
                    addDrink();
                    break;
                case "3":
                    addChip();
                    break;
                case "4":
                    checkout();
                    quit = true;
                    break;
                case "0":
                    if(currentOrder != null) currentOrder.cancelOrder();
                    System.out.println("\uD83D\uDDD1\uFE0F Order cancelled");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addSandwich() {
        printHeader("Build Your Sandwich");
        SandwichSize size = sandwichSize();
        BreadType bread = breadType();
        boolean isToasted = false;

        Sandwich s = new Sandwich("Custom Sandwich", size, bread, isToasted);
        toppings(s);

    }

    public void addDrink() {

    }

    public void addChip() {

    }

    public void checkout() {

    }

    private BreadType breadType() {
        while (true) {
            System.out.println(YELLOW + "\uD83E\uDD56 Please select your bread type:" + RESET);
            System.out.println("1️⃣  White");
            System.out.println("2️⃣  Wheat");
            System.out.println("3️⃣  Rye");
            System.out.println("4️⃣  Wrap");
            int c = ranges("\uD83D\uDC49 Choice: ", 1, 4);

            return switch (c) {
                case 1 -> BreadType.WHITE;
                case 2 -> BreadType.WHEAT;
                case 3 -> BreadType.RYE;
                case 4 -> BreadType.WRAP;
                default -> throw new IllegalStateException("Unexpected value: " + c);
            };
        }
    }

    private SandwichSize sandwichSize(){
        while (true) {
            System.out.println(YELLOW + "Please select your sandwich size:" + RESET);
            System.out.println("1️⃣  4 Inch");
            System.out.println("2️⃣  8 Inch");
            System.out.println("3️⃣  12 Inch");
            int c = ranges("\uD83D\uDC49 Choice: ", 1, 3);

            return switch (c) {
                case 1 -> SandwichSize.FOUR;
                case 2 -> SandwichSize.EIGHT;
                case 3 -> SandwichSize.TWELVE;
                default -> throw new IllegalStateException("Unexpected value: " + c);
            };
        }
    }
    private void toppings(Sandwich sandwich) {
        boolean quit = false;
        while(!quit) {
            System.out.println(CYAN + "=== Choose Toppings ===" + RESET);
            System.out.println("1) Add Meat (premium)");
            System.out.println("2) Add Cheese (premium)");
            System.out.println("3) Add Regular Topping");
            System.out.println("4) Done adding toppings");
            int c = ranges("👉 Choice: ", 1, 4);
            switch (c) {
                case 1 -> addMeatTopping(sandwich);
                case 2 -> addCheeseTopping(sandwich);
                case 3 -> addRegularTopping(sandwich);
                case 4 -> quit = true;
                default -> System.out.println("Invalid input, please try again.\n");
            }
        }
    }

    private void addRegularTopping(Sandwich sandwich){

    }

    private void addCheeseTopping(Sandwich sandwich){
        System.out.println(YELLOW + "Select a cheese:" + RESET);
        System.out.println("1) American");
        System.out.println("2) Provolone");
        System.out.println("3) Cheddar");
        System.out.println("4) Swiss");
        int c = ranges("👉 Choice: ", 1, 4);

        String ch = switch (c) {
            case 1 -> "american";
            case 2 -> "provolone";
            case 3 -> "cheddar";
            case 4 -> "swiss";
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };

        boolean extra = yesOrNo("Extra cheese? y/n: ");

        Cheese cheese = new Cheese(ch, extra);
        sandwich.addTopping(cheese);

        System.out.printf("✅ Added %s%s.%n",
                extra ? "extra " : "", ch);
    }

    private void addMeatTopping(Sandwich sandwich){
            System.out.println(YELLOW + "Select a meat:" + RESET);
            System.out.println("1) Steak");
            System.out.println("2) Ham");
            System.out.println("3) Salami");
            System.out.println("4) Roast Beef");
            System.out.println("5) Chicken");
            System.out.println("6) Bacon");
            int c = ranges("👉 Choice: ", 1, 6);

            String m = switch (c) {
                case 1 -> "steak";
                case 2 -> "ham";
                case 3 -> "salami";
                case 4 -> "roast beef";
                case 5 -> "chicken";
                case 6 -> "bacon";
                default -> throw new IllegalStateException("Unexpected value: " + c);
            };

            boolean extra = yesOrNo("Extra meat? y/n: ");

            Meat meat = new Meat(m, extra);
            sandwich.addTopping(meat);

        System.out.printf("✅ Added %s%s.%n",
                extra ? "extra " : "", m);
    }
    private int ranges(String choice, int min, int max) {
        while (true) {
            System.out.println(choice);
            String input = scanner.nextLine().trim();

            int c;
            try {
                c = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.printf("❌ Invalid input! Please enter a NUMBER between %d and %d.%n%n", min, max);
                continue;
            }

            if (c < min || c > max) {
                System.out.printf("❌ Please enter a number BETWEEN %d and %d.%n%n", min, max);
                continue;
            }

            return c;
        }
    }

    public boolean yesOrNo(String prompt){
        while(true) {
            System.out.println(prompt);
            String s = scanner.nextLine().trim();
            if(s.equalsIgnoreCase("y")) return true;
            if(s.equalsIgnoreCase("n")) return false;
        }
    }

}

