package com.epam.ok.storeCenter.model;

public class User extends BaseEntity {

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private Role role;

    public User(Integer id) {
        super(id);
    }

    public User(Integer id, boolean isDeleted) {
        super(id, isDeleted);
    }

    public User() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        user, admin
    }
}
