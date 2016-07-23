package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.Status;
import com.epam.ok.storeCenter.service.StatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetStatusAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ActionResult result = null;

        String id = request.getParameter("id");
        StatusService service = new StatusService();
        if (id == null) {
            try {
                List<Status> statusList = service.getAll();
                request.setAttribute("statusList", statusList);
                result = new ActionResult("statusList");
            } catch (DaoException e) {
                throw new ActionException();
            }
        }
        return result;
    }
}


