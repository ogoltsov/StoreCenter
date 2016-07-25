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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        if (isDeleted != that.isDeleted) return false;
        if (!id.equals(that.id)) return false;
        return title != null ? title.equals(that.title) : that.title == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
