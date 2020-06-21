package com.example.thindanAndroid.components.main.home;

import java.util.List;

public class ProfileCardModel {
    private int image;
    private String subject;
    private String name;
    private String description;
    private List<String> tags;

    public ProfileCardModel(int image, String subject, String name, String description, List<String> tags) {
        this.image = image;
        this.subject = subject;
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
