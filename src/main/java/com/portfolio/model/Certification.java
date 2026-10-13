package com.portfolio.model;

public class Certification {
    private String name;
    private String issuer;
    private String dateRange;
    private String icon;

    public Certification(String name, String issuer, String dateRange, String icon) {
        this.name = name;
        this.issuer = issuer;
        this.dateRange = dateRange;
        this.icon = icon;
    }

    public String getName() { return name; }
    public String getIssuer() { return issuer; }
    public String getDateRange() { return dateRange; }
    public String getIcon() { return icon; }
}
