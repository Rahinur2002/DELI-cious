package com.pluralsight.deli.common.enums;

public enum DrinkSize {
    SMALL(12),
    MEDIUM(16),
    LARGE(20);

    private final int ounces;

    DrinkSize(int ounces) {
        this.ounces = ounces;
    }

    public int getOunces() {
        return ounces;
    }
}
