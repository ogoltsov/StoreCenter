package com.epam.ok.storeCenter.model;

public class ResourceAuthor extends BaseEntity {

    private Integer resourceId;
    private Integer authoreId;

    public ResourceAuthor() {
    }

    public Integer getAuthoreId() {
        return authoreId;
    }

    public void setAuthoreId(Integer authoreId) {
        this.authoreId = authoreId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}
