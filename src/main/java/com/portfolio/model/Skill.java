package com.portfolio.model;

public class Skill {
    private String name;
    private int percentage;
    private String icon;
    private String color;

    public Skill(String name, int percentage, String icon, String color) {
        this.name = name;
        this.percentage = percentage;
        this.icon = icon;
        this.color = color;
    }

    public String getName() { return name; }
    public int getPercentage() { return percentage; }
    public String getIcon() { return icon; }
    public String getColor() { return color; }
}
