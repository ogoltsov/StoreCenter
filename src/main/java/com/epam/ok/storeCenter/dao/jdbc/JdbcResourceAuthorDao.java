package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.Author;
import com.epam.ok.storeCenter.model.ResourceAuthor;
import com.sun.istack.internal.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcResourceAuthorDao extends AbstractDao<ResourceAuthor> {

    private static final String TABLE_NAME = "ResourceAuthors";
    private static final String INSERT_AUTHOR_RESOURCE = "INSERT INTO ResourceAuthors (resId, authId) VALUES (?,?)";
//    private static final String DELETE_AUTHOR_RESOURCE = "DELETE FROM ResourceAuthors WHERE ";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_AUTHOR_RESOURCE;
    }

    @Override
    protected String getQueryForUpdate() {
        return null;
    }

    @Override
    protected ResourceAuthor getObjectFromResultSet(ResultSet rs) throws DaoException {
        ResourceAuthor author = new ResourceAuthor();
        try {
            author.setAuthoreId(rs.getInt("authId"));
            author.setResourceId(rs.getInt("resId"));
        } catch (SQLException e) {
            throw new DaoException();
        }
        return author;
    }

    @Override
    @Deprecated
    protected void setVariablesForPreparedStatementExceptId(ResourceAuthor author, PreparedStatement ps) throws DaoException {
    }
}
