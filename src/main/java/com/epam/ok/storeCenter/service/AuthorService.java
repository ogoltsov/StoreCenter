package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.*;

import java.util.*;

public class AuthorService {

    public List<Author> getAll() throws ServiceException {

        List<Author> authors;
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Author> authorDao = daoFactory.getDao(Author.class);
            authors = authorDao.findAll();
            Iterator i = authors.iterator();

            while (i.hasNext()) {
                Author author = (Author) i.next();
                if (author.isDeleted()) i.remove();
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not get all Authors", e);
        }
        return authors;
    }

    public Author getByPK(Integer id) throws ServiceException {
        Author author;
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Author> authorDao = daoFactory.getDao(Author.class);
            author = authorDao.findByPK(id);
            if (!author.isDeleted()) return author;
            else return null;
        } catch (DaoException e) {
            throw new ServiceException("Could not get Auhtor by PK", e);
        }
    }


    public Author findByFIO(String lastname, String firstname, String patronymic) throws ServiceException {

        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Author> dao = daoFactory.getDao(Author.class);
            Map<String, String> params = new HashMap<>();
            params.put("lastname", lastname);
            params.put("firstname", firstname);
            params.put("patronymic", patronymic);
            params.put("isDelete", "0");

            List<Author> authors = dao.findAllByParams(params);
            return authors.size() == 0 ? null : authors.get(0);
        } catch (DaoException e) {
            throw new ServiceException("Could not get author by FIO", e);
        }
    }

    public void save(Author author) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Author> dao = daoFactory.getDao(Author.class);
            if (author.getId() != null) dao.update(author);
            else dao.insert(author);
        } catch (DaoException e) {
            throw new ServiceException("Could not save Author", e);
        }
    }

    public boolean canDelete(Integer id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<ResourceAuthor> dao = daoFactory.getDao(ResourceAuthor.class);
            List<ResourceAuthor> resourceAuthors = dao.findAllByParams(Collections.singletonMap("authId", id.toString()));
            return (resourceAuthors.size() == 0);
        } catch (DaoException e) {
            throw new ServiceException("Could not delete Author", e);
        }
    }

    public void delete(int id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Author> dao = daoFactory.getDao(Author.class);
            dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Could not delete Auhtor by PK" ,e );
        }
    }

    public List<Resource> getResourcesForAuthor(Integer id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Resource> resourceGenericDao = daoFactory.getDao(Resource.class);
            GenericDao<ResourceAuthor> resourceAuthorGenericDao = daoFactory.getDao(ResourceAuthor.class);
            GenericDao<Category> categoryGenericDao = daoFactory.getDao(Category.class);
            GenericDao<Status> statusGenericDao = daoFactory.getDao(Status.class);
            GenericDao<Speciality> specialityGenericDao = daoFactory.getDao(Speciality.class);
            GenericDao<Author> authorGenericDao = daoFactory.getDao(Author.class);
            GenericDao<ResourceSpeciality> resourceSpecialityGenericDao = daoFactory.getDao(ResourceSpeciality.class);

            List<ResourceAuthor> resourceAuthorList =
                    resourceAuthorGenericDao.findAllByParams(Collections.singletonMap("authId", id.toString()));

            List<Resource> resources = new ArrayList<>();
            for (ResourceAuthor resourceAuthor : resourceAuthorList) {

                Resource resource = resourceGenericDao.findByPK(resourceAuthor.getResourceId());
                resource.setCategory(categoryGenericDao.findByPK(resource.getCategory().getId()));
                resource.setStatus(statusGenericDao.findByPK(resource.getStatus().getId()));
                resource.setSpecialities(ServiceUtil.getSpecialitiesForResource(resource.getId(),
                        resourceSpecialityGenericDao, specialityGenericDao));
                resource.setAuthors(ServiceUtil.getAuthorsForResource(resource.getId(), resourceAuthorGenericDao, authorGenericDao));


                resources.add(resource);
            }
            return resources;

        } catch (DaoException e) {
            throw new ServiceException("Could not get ResourceAuthorList", e);
        }
    }


}
