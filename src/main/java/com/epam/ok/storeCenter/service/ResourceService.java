package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.*;

import java.util.*;

public class ResourceService {


    public List<Resource> getAll() throws ServiceException {

        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Resource> rosourceDao = jdbcDaoFactory.getDao(Resource.class);
            GenericDao<Status> statusDao = jdbcDaoFactory.getDao(Status.class);
            GenericDao<Category> categoryDao = jdbcDaoFactory.getDao(Category.class);
            GenericDao<ResourceAuthor> resourceAuthorDao = jdbcDaoFactory.getDao(ResourceAuthor.class);
            GenericDao<ResourceSpeciality> resourceSpecialityDao = jdbcDaoFactory.getDao(ResourceSpeciality.class);
            GenericDao<Author> authorDao = jdbcDaoFactory.getDao(Author.class);
            GenericDao<Speciality> specialityDao = jdbcDaoFactory.getDao(Speciality.class);

            List<Resource> resources = rosourceDao.findAll();
            for (Resource resource : resources) {
                if (resource.isDeleted()) resources.remove(resource);
            }

            for (Resource resource : resources) {

                Status status = statusDao.findByPK(resource.getStatus().getId());
                Category category = categoryDao.findByPK(resource.getCategory().getId());
                resource.setStatus(status);
                resource.setCategory(category);

                List<ResourceAuthor> resourceAuthorList =
                        resourceAuthorDao.findAllByParams(Collections.singletonMap("resId", resource.getId().toString()));
                List<Author> authors = new ArrayList<>();

                for (ResourceAuthor resourceAuthor : resourceAuthorList) {
                    authors.add(authorDao.findByPK(resourceAuthor.getAuthoreId()));
                }
                resource.setAuthors(authors);

                List<Speciality> specialityList = new ArrayList<>();
                List<ResourceSpeciality> resourceSpecialityList =
                        resourceSpecialityDao.findAllByParams(Collections.singletonMap("resId", resource.getId().toString()));
                for (ResourceSpeciality resourceSpeciality : resourceSpecialityList){
                    Integer specialityId = resourceSpeciality.getSpecialityId();
                    specialityList.add(specialityDao.findByPK(specialityId));
                }
                resource.setSpecialities(specialityList);


            }
            return resources;
        } catch (DaoException e) {
            throw new ServiceException("Could not read resource from db", e);
        }
    }
}
