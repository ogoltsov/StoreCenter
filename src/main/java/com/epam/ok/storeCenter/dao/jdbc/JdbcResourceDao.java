package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.Category;
import com.epam.ok.storeCenter.model.Resource;
import com.epam.ok.storeCenter.model.Status;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class JdbcResourceDao extends AbstractDao<Resource> {

    private static final String TABLE_NAME = "Resource";
    private static final String INSERT_RESOURCE = "INSERT INTO Resource (title, date, statusId, typeId) VALUES (?,?,?,?)";
    private static final String UPDATE_RESOURCE = "UPDATE Resource SET title =?, date = ?, statusId = ?, typeId = ? WHERE id = ?";


    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_RESOURCE;
    }

    @Override
    protected String getQueryForUpdate() {
        return UPDATE_RESOURCE;
    }

    @Override
    protected Resource getObjectFromResultSet(ResultSet rs) throws DaoException {
        Resource resource = new Resource();
        try {
            resource.setId(rs.getInt("id"));
            resource.setTitle(rs.getString("title"));
            resource.setDate(rs.getDate("date"));
            resource.setCategory(new Category(rs.getInt("typeId")));
            resource.setStatus(new Status(rs.getInt("statusId")));
            resource.setDeleted(rs.getBoolean("isDelete"));

        } catch (SQLException e) {
            throw new DaoException();
        }
        return resource;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Resource resource, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, resource.getTitle());
            ps.setString(2, getMysqlDate(resource.getDate()));
            ps.setInt(3, resource.getStatus().getId());
            ps.setInt(4, resource.getCategory().getId());
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    private String getMysqlDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}