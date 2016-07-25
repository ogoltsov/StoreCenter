package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.ResourceSpeciality;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcResourceSpecialityDao extends AbstractDao<ResourceSpeciality> {

    private static final String TABLE_NAME = "ResourceSpeciality";
    private static final String INSERT_RESOURCE_SPECIALITY = "INSERT INTO ResourceSpeciality (resId, specId) VALUES (?, ?)";
    private static final String DELETE_RESOURCE_SPECIALITY = "DELETE FROM ResourceSpeciality WHERE resId = ? AND specId = ?";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_RESOURCE_SPECIALITY;
    }

    @Override
    protected String getQueryForUpdate() {
        return DELETE_RESOURCE_SPECIALITY;
    }

    @Override
    protected ResourceSpeciality getObjectFromResultSet(ResultSet rs) throws DaoException {
        ResourceSpeciality resourceSpeciality = new ResourceSpeciality();
        try {
            resourceSpeciality.setResourceId(rs.getInt("resId"));
            resourceSpeciality.setSpecialityId(rs.getInt("specId"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return resourceSpeciality;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(ResourceSpeciality rs, PreparedStatement ps) throws DaoException {
        try {
            ps.setInt(1,rs.getResourceId());
            ps.setInt(2,rs.getSpecialityId());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }
}
