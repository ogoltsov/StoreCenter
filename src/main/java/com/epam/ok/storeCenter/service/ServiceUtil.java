package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.model.Author;
import com.epam.ok.storeCenter.model.ResourceAuthor;
import com.epam.ok.storeCenter.model.ResourceSpeciality;
import com.epam.ok.storeCenter.model.Speciality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceUtil {

    public static List<Author> getAuthorsForResource(Integer resId, GenericDao<ResourceAuthor> resourceAuthorDao,
                                                     GenericDao<Author> authorDao) throws DaoException {
        List<ResourceAuthor> resourceAuthorList =
                resourceAuthorDao.findAllByParams(Collections.singletonMap("resId", resId.toString()));
        List<Author> authors = new ArrayList<>();

        for (ResourceAuthor resourceAuthor : resourceAuthorList) {
            authors.add(authorDao.findByPK(resourceAuthor.getAuthorId()));
        }
        return authors;
    }

    public static List<Speciality> getSpecialitiesForResource(Integer resId, GenericDao<ResourceSpeciality> resourceSpecialityDao,
                                                              GenericDao<Speciality> specialityDao) throws DaoException {
        List<Speciality> specialityList = new ArrayList<>();
        List<ResourceSpeciality> resourceSpecialityList =
                resourceSpecialityDao.findAllByParams(Collections.singletonMap("resId", resId.toString()));
        for (ResourceSpeciality resourceSpeciality : resourceSpecialityList) {
            Integer specialityId = resourceSpeciality.getSpecialityId();
            specialityList.add(specialityDao.findByPK(specialityId));
        }
        return specialityList;
    }
}
