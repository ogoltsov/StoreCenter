package com.epam.ok.storeCenter.model;

public class ResourceAuthor extends BaseEntity {

    private Integer resourceId;
    private Integer authorId;

    public ResourceAuthor() {
    }

    public ResourceAuthor(int resId, int authId) {
        this.resourceId = resId;
        this.authorId = authId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "ResourceAuthor{" +
                "resourceId=" + resourceId +
                ", authorId=" + authorId +
                '}';
    }
}
