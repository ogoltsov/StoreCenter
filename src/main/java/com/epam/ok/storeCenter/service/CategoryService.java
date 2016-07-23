package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.Category;

import java.util.List;

public class CategoryService {

    public List<Category> getAll() throws ServiceException {


        List<Category> categoryList;
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Category> categoryDao = jdbcDaoFactory.getDao(Category.class);
            categoryList = categoryDao.findAll();
            for (Category category : categoryList) {
                if (category.isDeleted()) categoryList.remove(category);
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return categoryList;
    }

    public Category getByPK(Integer id) throws ServiceException {
        Category category;
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Category> dao = jdbcDaoFactory.getDao(Category.class);
            category = dao.findByPK(id);
            if (!category.isDeleted()) return category;
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return null;
    }
}
