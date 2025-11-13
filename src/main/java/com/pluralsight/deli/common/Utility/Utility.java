package com.pluralsight.deli.common.Utility;

import java.util.Scanner;

public class Utility {
    private final Scanner scanner;

    public Utility(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void printHeader(String title) {
        System.out.println("=====================================");
        System.out.println("🍽️  " + title.toUpperCase());
        System.out.println("=====================================");
    }
}
