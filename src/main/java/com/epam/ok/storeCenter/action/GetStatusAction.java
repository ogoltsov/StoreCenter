package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.Validator;
import com.epam.ok.storeCenter.model.Status;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.StatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class GetStatusAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        View result;
        String id = request.getParameter("id");
        if (!isValid(id)) {
            throw new IllegalArgumentException("Illegal argument");
        } else {
            StatusService service = new StatusService();
            if (id == null) {
                try {
                    List<Status> statusList = service.getAll();
                    request.setAttribute("statusList", statusList);
                    result = new View("statusList");
                } catch (ServiceException e) {
                    throw new ActionException("Could not get Status list", e);
                }
            } else {
                try {
                    Status status = service.getByPK(Integer.parseInt(id));
                    request.setAttribute("status", status);
                    result = new View("status");
                } catch (ServiceException e) {
                    throw new ActionException("Could not get Status, id: " + id, e);
                }
            }
        }
        return result;
    }

    private boolean isValid(String id) {
        return ActionUtil.isValide(id, Validator.NOT_EMPTY_NUMBER);
    }
}


