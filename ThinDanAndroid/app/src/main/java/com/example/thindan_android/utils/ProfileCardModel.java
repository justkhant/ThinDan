package com.example.thindan_android.utils;

public class ProfileCardModel {
    private int image;
    private String subject;
    private String name;
    private String description;

    public ProfileCardModel(int image, String subject, String name, String description) {
        this.image = image;
        this.subject = subject;
        this.name = name;
        this.description = description;
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
}
