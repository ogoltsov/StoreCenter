package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao extends AbstractDao<User> {
    private static final String TABLE_NAME = "Users";
    private static final String INSERT_USER = "INSERT INTO Users (email, password, role, firstname, lastname, isDelete) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE Users SET email  = ?, password = ?, role = ?, firstname = ?, lastname = ?, isDelete = ? WHERE id = ?";

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
            user.setPassword(rs.getString("password"));
            user.setFirstname(rs.getString("firstname"));
            user.setLastname(rs.getString("lastname"));
            user.setRole(User.Role.valueOf(rs.getString("role")));
            user.setDeleted(rs.getBoolean("isDelete"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return user;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(User user, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());
            ps.setString(4, user.getFirstname());
            ps.setString(5, user.getLastname());
            ps.setBoolean(6, user.isDeleted());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }

    @Override
    protected String getQueryForUpdate() {
        return UPDATE_USER;
    }
}
