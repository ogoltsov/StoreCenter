package com.epam.ok.storeCenter.model;

public class Department extends Office {

    private College college;

    public Department(Integer id) {
        super(id);
    }

    public Department(Integer id, boolean isDeleted) {
        super(id, isDeleted);
    }

    public Department(Integer id, String title, String abbreviation) {
        super(id, title, abbreviation);
    }

    public Department(Integer id, boolean isDeleted, String title, String abbreviation) {
        super(id, isDeleted, title, abbreviation);
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
