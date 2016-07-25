package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.model.BaseEntity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao<T extends BaseEntity> implements GenericDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);
    private static final String SELECT_FROM = "SELECT * FROM ";
    private static final String WHERE_ID = " WHERE id = ";

    protected Connection connection;

    protected abstract String getTableName();

    protected abstract String getQueryForInsert();

    protected abstract String getQueryForUpdate();

    protected abstract T getObjectFromResultSet(ResultSet rs) throws DaoException;

    protected abstract void setVariablesForPreparedStatementExceptId(T t, PreparedStatement ps) throws DaoException;

    @Override
    public T insert(T t) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(getQueryForInsert(), Statement.RETURN_GENERATED_KEYS)) {
            setVariablesForPreparedStatementExceptId(t, ps);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                t.setId(rs.getInt(1));
                t.setDeleted(false);
            }
        } catch (SQLException e) {
            throw new DaoException("Could not insert Object to db", e);
        }
        logger.info("Insert new object: " + t);
        return t;
    }

    @Override
    public T findByPK(Integer id) throws DaoException {
        T object;
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_FROM + getTableName() + WHERE_ID + id);
            rs.next();
            object = getObjectFromResultSet(rs);

        } catch (SQLException e) {
            throw new DaoException("Could not find object by current id", e);
        }
        logger.info("Find object by PK: " + object);
        return object;
    }

    @Override
    public List<T> findAllByParams(Map<String, String> params) throws DaoException {
        List<T> objects = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(getQueryToFindAllByParams(params))) {
            while (rs.next()) {
                objects.add(getObjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Could not find object with this params", e);
        }
        logger.info("Find objects by params: " + params);
        return objects;
    }

    private String getQueryToFindAllByParams(Map<String, String> params) {
        String query = SELECT_FROM + getTableName() + " WHERE ";
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (params.size() == 1) {
                query += param.getKey() + " = '" + param.getValue() + "'";
                return query;
            } else {
                query += param.getKey() + " = '" + param.getValue() + "' AND ";
            }
        }
        return query.substring(0, query.length() - 5);
    }

    @Override
    public List<T> findAll() throws DaoException {
        List<T> objects = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_FROM + getTableName());
            while (rs.next()) {
                objects.add(getObjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Could not find all object", e);
        }
        logger.info("Find all users");
        return objects;
    }

    @Override
    public void update(T t) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(getQueryForUpdate())) {
            setVariablesForPreparedStatement(t, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Could not update Object in db", e);
        }
        logger.info("Update object from db: " + t);
    }

    private void setVariablesForPreparedStatement(T t, PreparedStatement ps) throws DaoException {
        setVariablesForPreparedStatementExceptId(t, ps);
        if (t.getId() != null) {
            int lastParameterIndex;
            try {
                lastParameterIndex = ps.getParameterMetaData().getParameterCount();
                ps.setInt(lastParameterIndex, t.getId());
            } catch (SQLException e) {
                throw new DaoException("Could not set variables for prepared statement", e);
            }
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("UPDATE " + getTableName() + " SET isDelete=1" + WHERE_ID + id);
        } catch (SQLException e) {
            throw new DaoException("Could not delete object by id", e);
        }
        logger.info("Delete object by id: " + id + "  from table: " + getTableName());
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
