package com.epam.ok.storeCenter.model;

public class Author extends BaseEntity {

    private String firstname;
    private String patronymic;
    private String lastname;

    public Author(Integer id) {
        super(id);
    }

    public Author() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Author author = (Author) o;

        if (!firstname.equals(author.firstname)) return false;
        if (!patronymic.equals(author.patronymic)) return false;
        return lastname.equals(author.lastname);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + patronymic.hashCode();
        result = 31 * result + lastname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
