package com.epam.ok.storeCenter.dao;

import com.epam.ok.storeCenter.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao extends AbstractDao<User> {
    private static final String TABLE_NAME = "Users";
    private static final String INSERT_USER = "INSERT INTO Users (login, pass, role, firstname, lastname, isDeleted) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE Users SET login  = ?, pass = ?, role = ?, firstname = ?, lastname = ? WHERE id = ?";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_USER;
    }

    @Override
    protected User getObjectFromResultSet(ResultSet rs) throws DaoException {
        User user = new User();
        try {
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("pass"));
            user.setFirstname(rs.getString("firstname"));
            user.setLastname(rs.getString("lastname"));
            user.setRole(User.Role.valueOf(rs.getString("role")));
        } catch (SQLException e) {
            throw new DaoException();
        }
        return user;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(User user, PreparedStatement ps) {

    }

    @Override
    protected String getQueryForUpdate() {
        return UPDATE_USER;
    }
}
