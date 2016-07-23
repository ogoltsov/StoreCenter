package com.epam.ok.storeCenter.model;

public class BaseEntity {

    private Integer id;
    private String title;
    private boolean isDeleted;

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public BaseEntity(Integer id, boolean isDeleted) {
        this.id = id;
        this.isDeleted = isDeleted;
    }

    public BaseEntity(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public BaseEntity() {

    }

    public BaseEntity(Integer id, String title, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.isDeleted = isDeleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
