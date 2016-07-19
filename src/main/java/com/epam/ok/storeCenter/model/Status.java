package com.epam.ok.storeCenter.model;

public class Status extends BaseEntity {

    private String title;

    public Status(Integer id) {
        super(id);
    }

    public Status(Integer id, String title) {
        super(id);
        this.title = title;
    }

    public Status(Integer id, boolean isDeleted, String title) {
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
