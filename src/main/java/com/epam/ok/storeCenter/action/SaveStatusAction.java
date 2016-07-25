package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Status;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.StatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class SaveStatusAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Status status = new Status();

        status.setId(id.equals("") ? null : Integer.parseInt(id));
        status.setTitle(title);
        status.setDescription(description);

        StatusService service = new StatusService();

        try {
            Status foundedStatus = service.getByTitle(title);

            if ((foundedStatus == null) || (foundedStatus.getId() == status.getId())) {
                service.save(status);
                return new View("status", true);
            } else {
                return forwardBackWithError(request, status);
            }
        } catch (ServiceException e) {
            throw new ActionException("Could not save Status", e);
        }


    }

    private View forwardBackWithError(HttpServletRequest request, Status status) {
        request.setAttribute("status", status);
        request.setAttribute("error", "Check fields");
        return new View("status");
    }
}
