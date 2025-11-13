package com.pluralsight.deli.ui;

import com.pluralsight.deli.common.enums.BreadType;
import com.pluralsight.deli.common.enums.SandwichSize;

import java.util.Calendar;
import java.util.Scanner;

import static com.pluralsight.deli.common.Utility.Utility.printHeader;

public class UserInterface {

    private Scanner scanner;
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

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    orderScreen();
                    break;
                case "0":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
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

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

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
                    break;
                case "0":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addSandwich() {
        printHeader("Build Your Sandwich");
        breadType();

    }

    public void addDrink() {

    }

    public void addChip() {

    }

    public void checkout() {

    }

    private BreadType breadType() {
        while (true) {
            System.out.println(YELLOW + "Please select your bread type:" + RESET);
            System.out.println("1️⃣  White");
            System.out.println("2️⃣  Wheat");
            System.out.println("3️⃣  Rye");
            System.out.println("4️⃣  Wrap");
            System.out.println("\uD83D\uDC49 Choice: ");
            String choice = scanner.nextLine();
            int c;
            try {
                c = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input, please enter as a number between 1 and 4.\n");
                continue;
            }
            if (c < 1 || c > 4) {
                System.out.println("❌ Please enter a valid number between 1-4. \n");
                continue;
            }
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
            System.out.println(YELLOW + "Please select your bread type:" + RESET);
            System.out.println("1️⃣  White");
            System.out.println("2️⃣  Wheat");
            System.out.println("3️⃣  Rye");
            System.out.println("4️⃣  Wrap");
            System.out.println("\uD83D\uDC49 Choice: ");
            String choice = scanner.nextLine();
            int c;
            try {
                c = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input, please enter as a number between 1 and 4.\n");
                continue;
            }
        }
    }
}

