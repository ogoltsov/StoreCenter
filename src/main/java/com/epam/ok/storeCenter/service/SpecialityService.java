package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.Speciality;

import java.util.List;

public class SpecialityService {


    public List<Speciality> getAll() throws ServiceException {

        List<Speciality> specialityList;
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Speciality> dao = daoFactory.getDao(Speciality.class);
            specialityList = dao.findAll();
            for (Speciality speciality : specialityList) {
                if (speciality.isDeleted()) specialityList.remove(speciality);
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return specialityList;
    }

}
