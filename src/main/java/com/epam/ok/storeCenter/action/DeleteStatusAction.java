package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.Validator;
import com.epam.ok.storeCenter.model.Status;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.StatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class DeleteStatusAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String id = request.getParameter("id");

        StatusService service = new StatusService();
        if (!isValid(id)) {
            throw new IllegalArgumentException("Illegal argument");
        } else {
            try {
                boolean canDelete = service.canDelete(Integer.parseInt(id));
                if (canDelete) {
                    service.delete(Integer.parseInt(id));
                    return new View("status", true);
                } else {
                    Status status = new Status();

                    String title = request.getParameter("title");
                    String description = request.getParameter("description");

                    status.setId(Integer.parseInt(id));
                    status.setTitle(title);
                    status.setDescription(description);


                    request.setAttribute("status", status);
                    request.setAttribute("error", "Sorry, You can't delete this :(");
                    return new View("status");

                }
            } catch (ServiceException e) {
                throw new ActionException("Could not delete Status, id: " + id, e);
            }
        }
    }

    private boolean isValid(String id) {
        return ActionUtil.isValide(id, Validator.NOT_EMPTY_NUMBER);
    }
}
