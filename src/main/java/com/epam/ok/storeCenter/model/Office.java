package com.epam.ok.storeCenter.model;

public class Office extends BaseEntity {

    private String title;
    private String abbreviation;

    public Office(Integer id) {
        super(id);
    }

    public Office(Integer id, boolean isDeleted) {
        super(id, isDeleted);
    }

    public Office(Integer id, String title, String abbreviation) {
        super(id);
        this.title = title;
        this.abbreviation = abbreviation;
    }

    public Office(Integer id, boolean isDeleted, String title, String abbreviation) {
        super(id, isDeleted);
        this.title = title;
        this.abbreviation = abbreviation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
