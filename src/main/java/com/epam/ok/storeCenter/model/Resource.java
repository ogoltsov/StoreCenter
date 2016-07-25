package com.epam.ok.storeCenter.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;
import java.util.List;

public class Resource extends BaseEntity {
    private Category category;
    private Status status;
    private DateTime date;
    private List<Author> authors;
    private List<Speciality> specialities;
    private Date formatedeDate;

    public Resource(Integer id) {
        super(id);
    }

    public Resource(Integer id, String title) {
        super(id);
        super.setTitle(title);
    }

    public Resource(Integer id, boolean isDeleted, String title) {
        super(id, title, isDeleted);
    }

    public Resource() {

    }

    public String getFormatedeDate() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        return date.toString(formatter);
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return super.getTitle();
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Resource resource = (Resource) o;

        if (!category.equals(resource.category)) return false;
        if (!status.equals(resource.status)) return false;
        if (!date.equals(resource.date)) return false;
        if (authors != null ? !authors.equals(resource.authors) : resource.authors != null) return false;
        return specialities != null ? specialities.equals(resource.specialities) : resource.specialities == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (specialities != null ? specialities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" + "id='" + super.getId() + '\'' +
                "title='" + super.getTitle() + '\'' +
                ", category=" + category +
                ", status=" + status +
                ", date=" + date +
                ", authors=" + authors +
                ", specialities=" + specialities +
                ", formatedeDate=" + formatedeDate +
                '}';
    }
}
