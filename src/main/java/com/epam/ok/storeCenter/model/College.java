package com.epam.ok.storeCenter.model;

import java.util.List;

public class College extends Office {

    private List<Department> departments;
    public College(Integer id) {
        super(id);
    }

    public College(Integer id, boolean isDeleted) {
        super(id, isDeleted);
    }

    public College(Integer id, String title, String abbreviation) {
        super(id, title, abbreviation);
    }

    public College(Integer id, boolean isDeleted, String title, String abbreviation) {
        super(id, isDeleted, title, abbreviation);
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
