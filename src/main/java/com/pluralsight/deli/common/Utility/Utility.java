package com.pluralsight.deli.common.Utility;

import java.util.Scanner;

public class Utility {

    public Utility(Scanner scanner) {
    }

    public static void printHeader(String title) {
        System.out.println("=====================================");
        System.out.println("🍽️  " + title.toUpperCase());
        System.out.println("=====================================");
    }
}
