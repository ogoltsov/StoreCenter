package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ResourceService {


    public List<Resource> getAll() throws ServiceException {

        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Resource> resourceDao = jdbcDaoFactory.getDao(Resource.class);
            GenericDao<Status> statusDao = jdbcDaoFactory.getDao(Status.class);
            GenericDao<Category> categoryDao = jdbcDaoFactory.getDao(Category.class);
            GenericDao<ResourceAuthor> resourceAuthorDao = jdbcDaoFactory.getDao(ResourceAuthor.class);
            GenericDao<ResourceSpeciality> resourceSpecialityDao = jdbcDaoFactory.getDao(ResourceSpeciality.class);
            GenericDao<Author> authorDao = jdbcDaoFactory.getDao(Author.class);
            GenericDao<Speciality> specialityDao = jdbcDaoFactory.getDao(Speciality.class);

            List<Resource> resources = resourceDao.findAll();
            Iterator i = resources.iterator();
            while (i.hasNext()) {
                Resource resource = (Resource) i.next();
                if (resource.isDeleted()) i.remove();
            }

            for (Resource resource : resources) {
                resource.setStatus(statusDao.findByPK(resource.getStatus().getId()));
                resource.setCategory(categoryDao.findByPK(resource.getCategory().getId()));
                resource.setAuthors(ServiceUtil.getAuthorsForResource(resource.getId(), resourceAuthorDao, authorDao));
                resource.setSpecialities(ServiceUtil.getSpecialitiesForResource(resource.getId(), resourceSpecialityDao, specialityDao));
            }
            return resources;
        } catch (DaoException e) {
            throw new ServiceException("Could not read resource from db", e);
        }
    }


    public Resource getByPK(int id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Resource> resourceDao = daoFactory.getDao(Resource.class);
            GenericDao<Status> statusDao = daoFactory.getDao(Status.class);
            GenericDao<Category> categoryDao = daoFactory.getDao(Category.class);
            GenericDao<ResourceAuthor> resourceAuthorDao = daoFactory.getDao(ResourceAuthor.class);
            GenericDao<ResourceSpeciality> resourceSpecialityDao = daoFactory.getDao(ResourceSpeciality.class);
            GenericDao<Author> authorDao = daoFactory.getDao(Author.class);
            GenericDao<Speciality> specialityDao = daoFactory.getDao(Speciality.class);

            Resource resource = resourceDao.findByPK(id);
            resource.setStatus(statusDao.findByPK(resource.getStatus().getId()));
            resource.setCategory(categoryDao.findByPK(resource.getCategory().getId()));
            resource.setAuthors(ServiceUtil.getAuthorsForResource(id, resourceAuthorDao, authorDao));
            resource.setSpecialities(ServiceUtil.getSpecialitiesForResource(id, resourceSpecialityDao, specialityDao));

            return resource;
        } catch (DaoException e) {
            throw new ServiceException("Could not get Resource by id", e);
        }
    }

    public Resource getByParams(Map<String, String> params) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Resource> dao = daoFactory.getDao(Resource.class);
            List<Resource> resources = dao.findAllByParams(params);
            return resources.size() == 0 ? null : resources.get(0);
        } catch (DaoException e) {
            throw new ServiceException("Could not get Resource by params");
        }
    }

    public void save(Resource resource) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Resource> dao = daoFactory.getDao(Resource.class);
            if (resource.getId() != null) dao.update(resource);
            else dao.insert(resource);
        } catch (DaoException e) {
            throw new ServiceException("Could not save Resource", e);
        }
    }

    public void delete(int id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Resource> dao = daoFactory.getDao(Resource.class);
            dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Could not delete Resource by PK", e);
        }
    }

    public void addAuthorForResource(ResourceAuthor ra) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<ResourceAuthor> dao = daoFactory.getDao(ResourceAuthor.class);

            dao.insert(ra);

        } catch (DaoException e) {
            throw new ServiceException("Could add Author for Resource", e);
        }
    }

    public void removeAuthorFromResource(ResourceAuthor ra) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<ResourceAuthor> dao = daoFactory.getDao(ResourceAuthor.class);
            dao.update(ra);
        } catch (DaoException e) {
            throw new ServiceException("Could not remove Author from Resource", e);
        }

    }

    public void addSpecialityForResource(ResourceSpeciality rs) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<ResourceSpeciality> dao = daoFactory.getDao(ResourceSpeciality.class);
            dao.insert(rs);
        } catch (DaoException e) {
            throw new ServiceException("Could not add Speciality fro Resource", e);
        }
    }

    public void removeSpecialityFromResource(ResourceSpeciality rs) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<ResourceSpeciality> dao = daoFactory.getDao(ResourceSpeciality.class);
            dao.update(rs);
        } catch (DaoException e) {
            throw new ServiceException("Could not remove Speciality from Resource", e);
        }
    }
}
