package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    public User performUserLogin(String email, String password) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            Map<String, String> params = new HashMap<>();
            params.put("email", email);
            params.put("password", password);
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            List<User> users = userDao.findAllByParams(params);
            if (!users.isEmpty() && !users.get(0).isDeleted()) {
                return users.get(0);
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not read user from db", e);
        }
        return null;
    }

    public List<User> getAll() {
        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.JDBC);
            GenericDao<User> dao = daoFactory.getDao(User.class);
             return dao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
