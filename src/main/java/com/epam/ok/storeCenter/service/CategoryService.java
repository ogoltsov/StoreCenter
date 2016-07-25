package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.Category;
import com.epam.ok.storeCenter.model.Resource;

import java.util.*;

public class CategoryService {

    public List<Category> getAll() throws ServiceException {
        List<Category> categoryList;
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Category> categoryDao = jdbcDaoFactory.getDao(Category.class);
            categoryList = categoryDao.findAll();
            Iterator i = categoryList.iterator();
            while (i.hasNext()){
                Category category = (Category) i.next();
                if (category.isDeleted()) i.remove();
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not get Categoty list", e);
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
            throw new ServiceException("Could not get Category by PK",e );
        }
        return null;
    }

    public boolean checkTitle(String title) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Category> dao = daoFactory.getDao(Category.class);
            List<Category> categories = dao.findAllByParams(Collections.singletonMap("title", title));
            return categories.size() == 0;
        } catch (DaoException e) {
            throw new ServiceException("Could not check title of Category", e);
        }
    }

    public void save(Category category) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Category> dao = daoFactory.getDao(Category.class);
            if (category.getId() != null) dao.update(category);
            else dao.insert(category);
        } catch (DaoException e) {
            throw new ServiceException("Could not save Category");
        }

    }

    public Category getByTitle(String title) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Category> dao = daoFactory.getDao(Category.class);
            List<Category> categories = dao.findAllByParams(Collections.singletonMap("title", title));
            if (categories.size() != 0) return categories.get(0);
        } catch (DaoException e) {
            throw new ServiceException("Could not get Category by title", e);
        }
        return null;
    }

    public boolean canDelete(Integer id) throws ServiceException {

        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Resource> dao = daoFactory.getDao(Resource.class);
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", id.toString());
            params.put("isDelete", "0");
            List<Resource> resources = dao.findAllByParams(params);
            return resources.size() == 0;
        } catch (DaoException e) {
            throw new ServiceException("Could not check delete Category by id", e);
        }
    }

    public void delete(int id) throws DaoException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Category> dao = daoFactory.getDao(Category.class);
            dao.delete(id);
        } catch (DaoException e) {
            throw new DaoException("Could not delete Category by id", e);
        }
    }
}
