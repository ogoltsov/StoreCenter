package com.epam.ok.storeCenter.service;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.dao.DaoFactory;
import com.epam.ok.storeCenter.dao.GenericDao;
import com.epam.ok.storeCenter.model.Status;

import java.util.List;

public class StatusService {

    public List<Status> getAll() throws DaoException {
        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.JDBC);
            GenericDao<Status> dao = daoFactory.getDao(Status.class);
            List<Status> statusList = dao.findAll();
            for (Status status : statusList) {
                if (status.isDeleted()) statusList.remove(status);
            }
            return statusList;
        } catch (DaoException e) {
            throw new DaoException();
        }

    }
}
