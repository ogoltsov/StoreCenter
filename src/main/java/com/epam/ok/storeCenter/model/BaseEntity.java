package com.epam.ok.storeCenter.model;

public class BaseEntity {

    private Integer id;

    private boolean isDeleted;

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public BaseEntity(Integer id, boolean isDeleted) {
        this.id = id;
        this.isDeleted = isDeleted;
    }

    public BaseEntity() {

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
