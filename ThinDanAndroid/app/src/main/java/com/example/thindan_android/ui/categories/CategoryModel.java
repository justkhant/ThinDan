package com.example.thindan_android.ui.categories;

import java.util.List;

public class CategoryModel {
    private String title;
    private String thindan_count;
    private String subcategories;
    private List<String> tags;

    public CategoryModel(String title, String thindan_count, String subcategories) {
        this.title = title;
        this.thindan_count = thindan_count;
        this.subcategories = subcategories;
        this.tags = tags;
    }

    public String getTitle() { return title; }
    public String getThindanCount() { return thindan_count; }
    public String getSubcategories() { return subcategories; }
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
