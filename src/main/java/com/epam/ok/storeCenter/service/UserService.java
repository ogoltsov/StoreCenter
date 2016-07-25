package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserService {

    public User performUserLogin(String email, String password) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            Map<String, String> params = new HashMap<>();
            params.put("email", email);
            params.put("password", password);
            params.put("isDelete", "0");
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

    public User registerUser(User user) throws ServiceException {
        User insertedUser;
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            user.setRole(User.Role.user);
            insertedUser = userDao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException("Could not read user from db", e);
        }
        return insertedUser;
    }

    public boolean checkEmail(String email) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);

            Map<String, String> params = new HashMap<>();
            params.put("email", email);
            params.put("isDelete", "0");

            List<User> users = userDao.findAllByParams(params);
            if (!users.isEmpty() && !users.get(0).isDeleted()) {
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not read user from db", e);
        }
        return true;
    }

    public User getByPK(Integer id) throws ServiceException {
        User user;
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<User> dao = daoFactory.getDao(User.class);
            user = dao.findByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("Could not get User by PK", e);
        }
        return user;
    }

    public List<User> getAll() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.JDBC);
            GenericDao<User> dao = daoFactory.getDao(User.class);
            List<User> users = dao.findAll();

            Iterator i = users.iterator();
            while (i.hasNext()) {
                User user = (User) i.next();
                if (user.isDeleted()) i.remove();
            }
            return users;
        } catch (DaoException e) {
            throw new ServiceException("Could not get all User", e);
        }
    }

    public void save(User user) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<User> dao = daoFactory.getDao(User.class);
            if (user.getId() != null) dao.update(user);
            else dao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException("Could not save User", e);
        }
    }

    public void deleteUser(int id) throws ServiceException {

        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<User> dao = daoFactory.getDao(User.class);
            dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Could not delete User", e);
        }

    }
}
