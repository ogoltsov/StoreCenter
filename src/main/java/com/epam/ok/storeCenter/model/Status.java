package com.epam.ok.storeCenter.model;

public class Status extends BaseEntity {


    private String description;

    public Status(Integer id) {
        super(id);
    }

    public Status(Integer id, String title) {
        super(id, title);
    }

    public Status(Integer id, boolean isDeleted, String title) {
        super(id, title, isDeleted);
    }

    public Status() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return super.getTitle();
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public String toString() {
        return super.toString() + "Status{" +
                "description='" + description + '\'' +
                '}';
    }
}
