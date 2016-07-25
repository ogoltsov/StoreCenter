package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.ResourceSpeciality;
import com.epam.ok.storeCenter.model.Speciality;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SpecialityService {


    public List<Speciality> getAll() throws ServiceException {

        List<Speciality> specialityList;
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Speciality> dao = daoFactory.getDao(Speciality.class);
            specialityList = dao.findAll();

            Iterator i = specialityList.iterator();
            while (i.hasNext()) {
                Speciality speciality = (Speciality) i.next();
                if (speciality.isDeleted()) i.remove();
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not get full list of Speciality", e);
        }
        return specialityList;
    }

    public Speciality getByPK(int id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Speciality> dao = daoFactory.getDao(Speciality.class);
            return dao.findByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("Could not get Speciality by id", e);
        }
    }

    public void save(Speciality speciality) throws ServiceException {

        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Speciality> dao = daoFactory.getDao(Speciality.class);
            if (speciality.getId() != null) dao.update(speciality);
            else dao.insert(speciality);
        } catch (DaoException e) {
            throw new ServiceException("Could not save speciality", e);
        }

    }

    public boolean canDeleteSpecialityById(Integer id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<ResourceSpeciality> dao = daoFactory.getDao(ResourceSpeciality.class);
            List<ResourceSpeciality> specialities = dao.findAllByParams(Collections.singletonMap("specId", id.toString()));

            return specialities.size() == 0;
        } catch (DaoException e) {
            throw new ServiceException("Could not get delete speciality from resoource", e);
        }
    }

    public void deleteById(int id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Speciality> dao = daoFactory.getDao(Speciality.class);
            dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Could not delete speciality", e);
        }
    }
}
