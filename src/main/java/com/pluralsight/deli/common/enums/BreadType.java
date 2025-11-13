package com.pluralsight.deli.common.enums;

public enum BreadType {
    WHITE("White Bread"),
    WHEAT("Wheat Bread"),
    RYE("Rye Bread)"),
    WRAP("Wrap");

    private final String label;

    BreadType(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
