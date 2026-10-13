package com.portfolio.model;

public class Project {
    private String title;
    private String description;
    private String imageUrl;
    private String githubUrl;
    private String liveUrl;
    private String[] techStack;
    private String category;

    public Project(String title, String description, String imageUrl,
                   String githubUrl, String liveUrl, String category, String... techStack) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.githubUrl = githubUrl;
        this.liveUrl = liveUrl;
        this.category = category;
        this.techStack = techStack;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public String getGithubUrl() { return githubUrl; }
    public String getLiveUrl() { return liveUrl; }
    public String[] getTechStack() { return techStack; }
    public String getCategory() { return category; }
}
