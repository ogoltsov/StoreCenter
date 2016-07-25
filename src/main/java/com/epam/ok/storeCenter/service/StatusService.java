package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.dao.jdbc.JdbcDaoFactory;
import com.epam.ok.storeCenter.model.Resource;
import com.epam.ok.storeCenter.model.Status;

import java.util.*;

public class StatusService {

    public List<Status> getAll() throws ServiceException {
        try (DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.JDBC)) {

            GenericDao<Status> dao = daoFactory.getDao(Status.class);
            List<Status> statusList = dao.findAll();
            Iterator i = statusList.iterator();
            while (i.hasNext()) {
                Status status = (Status) i.next();
                if (status.isDeleted()) i.remove();
            }
            return statusList;
        } catch (DaoException e) {
            throw new ServiceException("Could not get list of Status",e);
        }

    }

    public Status getByPK(int id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Status> dao = daoFactory.getDao(Status.class);
            return dao.findByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("Could not get Status by PK", e);
        }
    }

    public boolean canDelete(Integer id) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Resource> dao = daoFactory.getDao(Resource.class);
            Map<String, String> parmas = new HashMap<>();
            parmas.put("statusId", id.toString());
            parmas.put("isDelete", "0");
            List<Resource> resources = dao.findAllByParams(parmas);
            return resources.size() == 0;
        } catch (DaoException e) {
            throw new ServiceException("Could not check allowed to delete Status", e);
        }

    }

    public void delete(int id) throws ServiceException {

        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Status> dao = daoFactory.getDao(Status.class);
            dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Could not delete Status", e);
        }

    }

    public Status getByTitle(String title) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Status> dao = daoFactory.getDao(Status.class);
            List<Status> statuses = dao.findAllByParams(Collections.singletonMap("title", title));
            if (statuses.size() != 0) return statuses.get(0);
            else return null;
        } catch (DaoException e) {
            throw new ServiceException("Could not get Status by title", e);
        }
    }

    public void save(Status status) throws ServiceException {
        try (DaoFactory daoFactory = new JdbcDaoFactory()) {
            GenericDao<Status> dao = daoFactory.getDao(Status.class);
            if (status.getId() != null) dao.update(status);
            else dao.insert(status);
        } catch (DaoException e) {
            throw new ServiceException("Could not save Status", e);
        }
    }
}
