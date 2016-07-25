package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcStatusDao extends AbstractDao<Status> {

    private static final String TABLE_NAME = "Status";
    private static final String INSERT_STATUS = "INSERT INTO Status (title, description, isDelete) VALUES (?,?,?)";
    private static final String UPDATE_STATUS = "UPDATE Status SET title = ?, description = ?, isDelete = ? WHERE id = ?";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_STATUS;
    }

    @Override
    protected String getQueryForUpdate() {
        return UPDATE_STATUS;
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected Status getObjectFromResultSet(ResultSet rs) throws DaoException {
        Status status = new Status();
        try {
            status.setId(rs.getInt("id"));
            status.setTitle(rs.getString("title"));
            status.setDeleted(rs.getBoolean("isDelete"));
            status.setDescription(rs.getString("description"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return status;
    }

    @Override
    @SuppressWarnings("Duplicates")
    protected void setVariablesForPreparedStatementExceptId(Status status, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, status.getTitle());
            ps.setString(2, status.getDescription());
            ps.setBoolean(3, status.isDeleted());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }

    }
}
