package com.example.thindanAndroid.components.main.home;

public class SubjectCardModel {
    private int image;
    private String subjectTitle;
    private String subjectCategories;

    public SubjectCardModel(int image, String subjectTitle, String subjectCategories) {
        this.image = image;
        this.subjectTitle = subjectTitle;
        this.subjectCategories = subjectCategories;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectCategories() {
        return subjectCategories;
    }

    public void setSubjectCategories(String subjectCategories) {
        this.subjectCategories = subjectCategories;
    }
}
