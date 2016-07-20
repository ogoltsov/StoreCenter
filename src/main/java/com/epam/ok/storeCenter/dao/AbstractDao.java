package com.epam.ok.storeCenter.dao;

import com.epam.ok.storeCenter.model.BaseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao<T extends BaseEntity> implements GenericDao<T> {

    private static final String SELECT_FROM = "SELECT * FROM ";
    private static final String WHERE_ID = "WHERE id = ";

    protected Connection connection;

    protected abstract String getTableName();

    protected abstract String getQueryForInsert();

    protected abstract String getQueryForUpdate();

    protected abstract T getObjectFromResultSet(ResultSet rs) throws DaoException;

    protected abstract void setVariablesForPreparedStatementExceptId(T t, PreparedStatement ps);

    @Override
    public T insert(T t) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(getQueryForInsert(), Statement.RETURN_GENERATED_KEYS)) {
            setVariablesForPreparedStatementExceptId(t, ps);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            t.setId(rs.getInt(1));
//            logger.debug("{} inserted", t);
        } catch (SQLException e) {
            throw new DaoException("Could not insert Object to db", e);
        }
        return t;
    }

    @Override
    public T findByPK(Integer id) throws DaoException {
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_FROM + getTableName() + WHERE_ID + id);
            rs.next();
            T object = getObjectFromResultSet(rs);
            return object;
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public List<T> findAllByParams(Map<String, String> params) throws DaoException {
        List<T> objects = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(getQueryToFindAllByParams(params))) {
            while (rs.next()) {
                objects.add(getObjectFromResultSet(rs));
            }
//            logger.debug("Get entity list by current params: {} - {}", params, objects);
        } catch (SQLException e) {
            throw new DaoException("Could not find object with this params", e);
        }
        return objects;
    }

    private String getQueryToFindAllByParams(Map<String, String> params) {
        String query = SELECT_FROM + getTableName() + "WHERE ";
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
            throw new DaoException();
        }
        return objects;
    }

    @Override
    public List<T> findAll(int pageNumber, int pageSize) throws DaoException {
        return null;
    }

    @Override
    public void update(T t) throws DaoException {

    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public int getNotDeletedCount() throws DaoException {
        return 0;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
