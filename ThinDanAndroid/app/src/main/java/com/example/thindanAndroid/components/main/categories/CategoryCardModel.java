package com.example.thindanAndroid.components.main.categories;

public class CategoryCardModel {
    private int image;
    private String subjectTitle;
    private String subjectCategories;
    private String count;

    public CategoryCardModel(int image, String subjectTitle, String subjectCategories, String count) {
        this.image = image;
        this.subjectTitle = subjectTitle;
        this.subjectCategories = subjectCategories;
        this.count = count;
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

    public String getCount() {
        return subjectTitle;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
