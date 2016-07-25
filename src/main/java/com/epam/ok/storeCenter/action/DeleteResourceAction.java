package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.service.ResourceService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class DeleteResourceAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String id = request.getParameter("id");

        ResourceService service = new ResourceService();
        try {
            service.delete(Integer.parseInt(id));
            return new View("resource", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not delete Resource, id: " + id, e);
        }

    }
}
