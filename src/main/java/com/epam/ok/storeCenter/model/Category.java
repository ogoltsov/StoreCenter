package com.epam.ok.storeCenter.model;

public class Category extends BaseEntity {

    private String title;

    public Category(Integer id) {
        super(id);
    }

    public Category(Integer id, String title) {
        super(id);
        this.title = title;
    }

    public Category(Integer id, boolean isDeleted, String title) {
        super(id, isDeleted);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
