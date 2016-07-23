package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.Author;

import java.util.List;

public class AuthorService {

    public List<Author> getAll() throws ServiceException {

        List<Author> authors;
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Author> authorDao = daoFactory.getDao(Author.class);
            authors = authorDao.findAll();
            for (Author author : authors) {
                if (author.isDeleted()) authors.remove(author);
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return authors;
    }

    public Author getByPK(Integer id) throws ServiceException {
        Author author;
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Author> authorDao = daoFactory.getDao(Author.class);
            author = authorDao.findByPK(id);
            if (!author.isDeleted()) return author;
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return null;
    }


}
