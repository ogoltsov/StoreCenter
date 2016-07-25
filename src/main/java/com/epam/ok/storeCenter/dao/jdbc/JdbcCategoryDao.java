package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCategoryDao extends AbstractDao<Category> {

    private static final String TABLE_NAME = "Category";
    private static final String INSERT_CATEGORY = "INSERT INTO Category (title, description, isDelete) VALUES (?, ?, ?)";
    private static final String UPDATE_CATEGORY = "UPDATE Category SET title = ?, description = ?, isDelete = ? WHERE id = ?";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_CATEGORY;
    }

    @Override
    protected String getQueryForUpdate() {
        return UPDATE_CATEGORY;
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected Category getObjectFromResultSet(ResultSet rs) throws DaoException {
        Category category = new Category();
        try {
            category.setId(rs.getInt("id"));
            category.setTitle(rs.getString("title"));
            category.setDeleted(rs.getBoolean("isDelete"));
            category.setDescription(rs.getString("description"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return category;
    }

    @Override
    @SuppressWarnings("Duplicates")
    protected void setVariablesForPreparedStatementExceptId(Category category, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, category.getTitle());
            ps.setString(2, category.getDescription());
            ps.setBoolean(3, category.isDeleted());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }
}
