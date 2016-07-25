package com.epam.ok.storeCenter.model;

public class Speciality extends BaseEntity {

    private String code;

    public Speciality(Integer id) {
        super(id);
    }

    public Speciality(Integer id, String title, String code) {
        super(id, title);
        this.code = code;
    }

    public Speciality() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return super.getTitle();
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Speciality that = (Speciality) o;

        if (!this.getTitle().equals(that.getTitle())) return false;
        return code.equals(that.code);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Speciality{" +
                "code='" + code + '\'' +
                '}';
    }
}
