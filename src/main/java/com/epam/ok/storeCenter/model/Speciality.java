package com.epam.ok.storeCenter.model;

public class Speciality extends BaseEntity {

    private String title;
    private String code;

    public Speciality(Integer id) {
        super(id);
    }

    public Speciality(Integer id, String title, String code) {
        super(id);
        this.title = title;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
