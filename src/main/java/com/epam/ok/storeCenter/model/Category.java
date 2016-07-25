package com.epam.ok.storeCenter.model;

public class Category extends BaseEntity {

    private String description;

    public Category(Integer id) {
        super(id);
    }

    public Category(Integer id, String title) {
        super(id, title);
    }

    public Category(Integer id, boolean isDeleted, String title) {
        super(id, title, isDeleted);
    }

    public Category() {
        super();
    }

    public String getTitle() {
        return super.getTitle();
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + "Category{" +
                "description='" + description + '\'' +
                '}';
    }
}
