package com.portfolio.model;

public class Experience {
    private String company;
    private String role;
    private String period;
    private String location;
    private String description;
    private String[] highlights;

    public Experience(String company, String role, String period,
                      String location, String description, String... highlights) {
        this.company = company;
        this.role = role;
        this.period = period;
        this.location = location;
        this.description = description;
        this.highlights = highlights;
    }

    public String getCompany() { return company; }
    public String getRole() { return role; }
    public String getPeriod() { return period; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String[] getHighlights() { return highlights; }
}
