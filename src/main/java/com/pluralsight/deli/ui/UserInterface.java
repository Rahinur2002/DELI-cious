package com.pluralsight.deli.ui;

import com.pluralsight.deli.common.enums.BreadType;
import com.pluralsight.deli.common.enums.DrinkSize;
import com.pluralsight.deli.common.enums.SandwichSize;
import com.pluralsight.deli.orders.Order;
import com.pluralsight.deli.products.chips.Chip;
import com.pluralsight.deli.products.drinks.Drink;
import com.pluralsight.deli.products.sandwiches.Sandwich;
import com.pluralsight.deli.products.sandwiches.toppings.RegularTopping;
import com.pluralsight.deli.products.sandwiches.toppings.Sauce;
import com.pluralsight.deli.products.sandwiches.toppings.Side;
import com.pluralsight.deli.products.sandwiches.toppings.premium.Cheese;
import com.pluralsight.deli.products.sandwiches.toppings.premium.Meat;
import com.pluralsight.deli.receipts.ReceiptFile;

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

            System.out.print("\uD83D\uDCE5➡\uFE0F Input: ");
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

            System.out.print("\uD83D\uDCE5➡\uFE0F Input: ");
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
        boolean isToasted = yesOrNo("Toasted? Y/N: ");

        Sandwich s = new Sandwich("Custom Sandwich", size, bread, isToasted);
        toppings(s);

        currentOrder.addSandwich(s);
        System.out.println("✅ Sandwich added!");
    }

    public void addDrink() {
        System.out.println(YELLOW + "Select The Size Of The Drink:" + RESET);
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");
        System.out.println("4. Go Back");

        int c = ranges("\uD83D\uDCE5➡\uFE0F Input: ", 1, 4);

        if (c == 4) {
            goBack();
            return;
        }
        DrinkSize s = switch (c) {
            case 1 -> DrinkSize.SMALL;
            case 2 -> DrinkSize.MEDIUM;
            case 3 -> DrinkSize.LARGE;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };

        System.out.println("Enter the flavor you want: ");
        String flavor = scanner.nextLine().trim();
        if (flavor.isEmpty()) flavor = "Drink";

        Drink drinks = new Drink(flavor, s);
        currentOrder.addDrink(drinks);

        System.out.printf("✅ Added %s (%s).%n", flavor, s);
    }

    public void addChip() {
        System.out.println(YELLOW + "Select The Chips You Want: " + RESET);
        System.out.println("1. Doritos");
        System.out.println("2. Cheetos");
        System.out.println("3. Pringles");
        System.out.println("4. Go Back");

        int c = ranges("\uD83D\uDCE5➡\uFE0F Input: ", 1, 4);

        if (c == 4) {
            goBack();
            return;
        }

        String s = switch (c) {
            case 1 -> "doritos";
            case 2 -> "Cheetos";
            case 3 -> "pringles";
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };

        Chip chips = new Chip(s);
        currentOrder.addChip(chips);

        System.out.printf("✅ Added %s.%n", s);
    }

    public void checkout() {
        if(currentOrder == null) {
            System.out.println("No current order");
            return;
        }
        System.out.println("\n" + CYAN + "=== ORDER SUMMARY ===" + RESET);
        System.out.println(currentOrder.printDisplay());

        System.out.println("1. Confirm Checkout");
        System.out.println("2. Cancel Order");
        String choice = scanner.nextLine().trim();

        if(choice.equalsIgnoreCase("1")){
            new ReceiptFile().saveReceipt(currentOrder);
            System.out.printf("🎉 Thank you for coming in! Your total is $%.2f%n", currentOrder.getTotal());
        }else if (choice.equalsIgnoreCase("2")) {
            System.out.println("Cancelling Order");
            currentOrder.cancelOrder();
        }
    }

    private BreadType breadType() {
            System.out.println(YELLOW + "\uD83E\uDD56 Please select your bread type:" + RESET);
            System.out.println("1️⃣  White");
            System.out.println("2️⃣  Wheat");
            System.out.println("3️⃣  Rye");
            System.out.println("4️⃣  Wrap");
            int c = ranges("\uD83D\uDCE5➡\uFE0F Input: ", 1, 4);

            return switch (c) {
                case 1 -> BreadType.WHITE;
                case 2 -> BreadType.WHEAT;
                case 3 -> BreadType.RYE;
                case 4 -> BreadType.WRAP;
                default -> throw new IllegalStateException("Unexpected value: " + c);
            };
    }

    private SandwichSize sandwichSize(){
            System.out.println(YELLOW + "Please select your sandwich size:" + RESET);
            System.out.println("1️⃣  4 Inch");
            System.out.println("2️⃣  8 Inch");
            System.out.println("3️⃣  12 Inch");
            int c = ranges("\uD83D\uDCE5➡\uFE0F Input: ", 1, 3);

            return switch (c) {
                case 1 -> SandwichSize.FOUR;
                case 2 -> SandwichSize.EIGHT;
                case 3 -> SandwichSize.TWELVE;
                default -> throw new IllegalStateException("Unexpected value: " + c);
            };
    }
    private void toppings(Sandwich sandwich) {
        boolean quit = false;
        while(!quit) {
            System.out.println(CYAN + "=== Choose Toppings ===" + RESET);
            System.out.println("1. Add Meat (premium)");
            System.out.println("2. Add Cheese (premium)");
            System.out.println("3. Add Regular Topping");
            System.out.println("4. Add Sauces");
            System.out.println("5. Add Sides");
            System.out.println("6. Done adding toppings");
            int c = ranges("\uD83D\uDCE5➡\uFE0F Input:", 1, 6);
            switch (c) {
                case 1 -> addMeatTopping(sandwich);
                case 2 -> addCheeseTopping(sandwich);
                case 3 -> addRegularTopping(sandwich);
                case 4 -> addSauces(sandwich);
                case 5 -> addSides(sandwich);
                case 6 -> quit = true;
                default -> System.out.println("Invalid input, please try again.\n");
            }
        }
    }

    private void addSides(Sandwich sandwich){
        System.out.println(YELLOW + "Select Sides:" + RESET);
        System.out.println("1. Au Jus");
        System.out.println("2. Sauce");
        System.out.println("3. Go Back");

        int c = ranges("\uD83D\uDCE5➡\uFE0F Input: ", 1, 3);

        if (c == 3) {
            goBack();
            return;
        }
        String s = switch (c) {
            case 1 -> "Au Jus";
            case 2 -> "Sauce";
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };

        Side sides = new Side(s);
        sandwich.addTopping(sides);

        System.out.printf("✅ Added %s.%n", s);
    }

    private void addSauces(Sandwich sandwich) {
        System.out.println(YELLOW + "Select Sauces:" + RESET);
        System.out.println("1. Mayo");
        System.out.println("2. Mustard");
        System.out.println("3. Ketchup");
        System.out.println("4. Ranch");
        System.out.println("5. Thousand Islands");
        System.out.println("6. Vinaigrette");
        System.out.println("7. Go Back");

        int c = ranges("\uD83D\uDCE5➡\uFE0F Input: ", 1, 7);

        if (c == 7) {
            goBack();
            return;
        }
        String s = switch (c) {
            case 1 -> "mayo";
            case 2 -> "mustard";
            case 3 -> "ketchup";
            case 4 -> "ranch";
            case 5 -> "thousand islands";
            case 6 -> "vinaigrette";
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };

        Sauce sauces = new Sauce(s);
        sandwich.addTopping(sauces);

        System.out.printf("✅ Added %s.%n", s);
    }

    private void addRegularTopping(Sandwich sandwich){
        System.out.println(YELLOW + "Select a regular topping:" + RESET);
        System.out.println("1. Lettuce");
        System.out.println("2. Peppers");
        System.out.println("3. Onions");
        System.out.println("4. Tomatoes");
        System.out.println("5. Jalapeños");
        System.out.println("6. Cucumbers");
        System.out.println("7. Pickles");
        System.out.println("8. Guacamole");
        System.out.println("9. Mushrooms");
        System.out.println("10. Go Back");

        int c = ranges("\uD83D\uDCE5➡\uFE0F Input: ", 1, 10);

        if (c == 10) {
            goBack();
            return;
        }

        String t = switch (c) {
            case 1 -> "lettuce";
            case 2 -> "peppers";
            case 3 -> "onions";
            case 4 -> "tomatoes";
            case 5 -> "jalapenos";
            case 6 -> "cucumbers";
            case 7 -> "pickles";
            case 8 -> "guacamole";
            case 9 -> "mushrooms";
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };

        RegularTopping topping = new RegularTopping(t);
        sandwich.addTopping(topping);

        System.out.printf("✅ Added %s.%n", t);
    }

    private void addCheeseTopping(Sandwich sandwich){
        System.out.println(YELLOW + "Select a cheese:" + RESET);
        System.out.println("1. American");
        System.out.println("2. Provolone");
        System.out.println("3. Cheddar");
        System.out.println("4. Swiss");
        System.out.println("5. Go Back");
        int c = ranges("\uD83D\uDCE5➡\uFE0F Input: ", 1, 5);

        if (c == 5) {
            goBack();
            return;
        }

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

        System.out.printf("✅ Added %s%s%s%n",
                extra ? "extra " : "", ch, " cheese.");
    }

    private void addMeatTopping(Sandwich sandwich){
            System.out.println(YELLOW + "Select a meat:" + RESET);
            System.out.println("1. Steak");
            System.out.println("2. Ham");
            System.out.println("3. Salami");
            System.out.println("4. Roast Beef");
            System.out.println("5. Chicken");
            System.out.println("6. Bacon");
            System.out.println("7. Go Back");
            int c = ranges("\uD83D\uDCE5➡\uFE0F Input: ", 1, 7);

        if (c == 7) {
            goBack();
            return;
        }

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

    public void goBack() {
            System.out.println("↩\uFE0F Going Back...\n");
        }
    }



