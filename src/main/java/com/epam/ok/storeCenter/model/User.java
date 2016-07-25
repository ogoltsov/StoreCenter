package com.epam.ok.storeCenter.model;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;
        if (!password.equals(user.password)) return false;
        if (!firstname.equals(user.firstname)) return false;
        return lastname.equals(user.lastname);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{id:" + super.getId() + ", email: " + email + ", role: " + role + "}";
    }

    public enum Role {
        user, moderator, guest, admin;

        public static List<Role> getRoles() {
            List<Role> roles = new ArrayList<>();
            roles.add(user);
            roles.add(moderator);

            return roles;
        }
    }
}
