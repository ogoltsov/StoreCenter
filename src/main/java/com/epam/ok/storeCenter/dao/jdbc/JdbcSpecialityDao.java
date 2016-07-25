package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.Speciality;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSpecialityDao extends AbstractDao<Speciality> {

    private static final String TABLE_NAME = "Speciality";
    private static final String INSERT_SPECIALITY = "INSERT INTO Speciality (code, title) VALUES (?,?)";
    private static final String UPDATE_SPECIALITY = "UPDATE Speciality SET  code = ?, title = ? WHERE id =?";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_SPECIALITY;
    }

    @Override
    protected String getQueryForUpdate() {
        return UPDATE_SPECIALITY;
    }

    @Override
    protected Speciality getObjectFromResultSet(ResultSet rs) throws DaoException {
        Speciality speciality = new Speciality();
        try {
            speciality.setId(rs.getInt("id"));
            speciality.setCode(rs.getString("code"));
            speciality.setTitle(rs.getString("title"));
            speciality.setDeleted(rs.getBoolean("isDelete"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return speciality;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Speciality speciality, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, speciality.getCode());
            ps.setString(2, speciality.getTitle());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }

    }
}
