package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.ResourceAuthor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcResourceAuthorDao extends AbstractDao<ResourceAuthor> {

    private static final String TABLE_NAME = "ResourceAuthors";
    private static final String INSERT_AUTHOR_RESOURCE = "INSERT INTO ResourceAuthors (resId, authId) VALUES (?,?)";
    private static final String DELETE_AUTHOR_RESOURCE = "DELETE FROM ResourceAuthors WHERE resId =? AND authId = ?";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_AUTHOR_RESOURCE;
    }

    @Override
    @Deprecated
    protected String getQueryForUpdate() {
        return DELETE_AUTHOR_RESOURCE;
    }

    @Override
    protected ResourceAuthor getObjectFromResultSet(ResultSet rs) throws DaoException {
        ResourceAuthor author = new ResourceAuthor();
        try {
            author.setAuthorId(rs.getInt("authId"));
            author.setResourceId(rs.getInt("resId"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return author;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(ResourceAuthor ra, PreparedStatement ps) throws DaoException {
        try {
            ps.setInt(1, ra.getResourceId());
            ps.setInt(2, ra.getAuthorId());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }
}
