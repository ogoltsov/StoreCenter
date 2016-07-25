package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.ResourceAuthor;
import com.epam.ok.storeCenter.service.ResourceService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class RemoveAuthorFromResourceAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String resId = request.getParameter("resId");
        String authorId = request.getParameter("authorId");

        ResourceService service = new ResourceService();
        ResourceAuthor ra = new ResourceAuthor();
        ra.setAuthorId(Integer.parseInt(authorId));
        ra.setResourceId(Integer.parseInt(resId));

        try {
            service.removeAuthorFromResource(ra);
            return new View("resource", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not remove Author from Resource", e);
        }

    }


}
