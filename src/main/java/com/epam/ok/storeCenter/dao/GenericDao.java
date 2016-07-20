package com.epam.ok.storeCenter.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao<T> {

    T insert(T t) throws DaoException;

    T findByPK(Integer id) throws DaoException;

    List<T> findAllByParams(Map<String, String> params) throws DaoException;

    List<T> findAll() throws DaoException;

    List<T> findAll(int pageNumber, int pageSize) throws DaoException;

    void update(T t) throws DaoException;

    void delete(Integer id) throws DaoException;

    int getNotDeletedCount() throws DaoException;

}
