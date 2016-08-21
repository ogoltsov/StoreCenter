package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.model.BaseEntity;
import com.epam.ok.storeCenter.pool.ConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private Connection connection;

    public JdbcDaoFactory() {
        try {
            DataSource pool = ConnectionPool.getInstance();
            this.connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends BaseEntity> GenericDao<T> getDao(Class<T> clazz) throws DaoException {
        AbstractDao<T> daoObject;
        try {
            String inputClassName = clazz.getSimpleName();
            String packageName = this.getClass().getPackage().getName();
            String resultClassName = String.format("%s.Jdbc%sDao", packageName, inputClassName);
            daoObject = (AbstractDao<T>) Class.forName(resultClassName).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DaoException("Dao object for " + clazz + " not found.", e);
        }
        daoObject.setConnection(connection);
        return daoObject;
    }

    @Override
    public void close() throws DaoException {
        try {
            this.connection.setAutoCommit(true);
            this.connection.close();
        } catch (SQLException e) {
            throw new DaoException("Could not close PooledConnection", e);
        }

    }
}
