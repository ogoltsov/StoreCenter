package com.epam.ok.storeCenter.model;

import java.time.LocalDate;
import java.util.List;

public class Resource extends BaseEntity {
    private String title;
    private Category category;
    private Status status;
    private LocalDate date;
    private List<Author> authors;
    private List<Speciality> specialities;

    public Resource(Integer id) {
        super(id);
    }

    public Resource(Integer id, String title) {
        super(id);
        this.title = title;
    }

    public Resource(Integer id, boolean isDeleted, String title) {
        super(id, isDeleted);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}
