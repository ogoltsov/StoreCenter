package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.Validator;
import com.epam.ok.storeCenter.model.ResourceAuthor;
import com.epam.ok.storeCenter.service.ResourceService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class AddAuthorForResourceAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String authorId = request.getParameter("authorId");
        String resourceId = request.getParameter("resourceId");

        if (isValide(authorId, resourceId)) {
            ResourceService service = new ResourceService();
            ResourceAuthor ra = new ResourceAuthor();
            ra.setResourceId(Integer.parseInt(resourceId));
            ra.setAuthorId(Integer.parseInt(authorId));
            try {
                service.addAuthorForResource(ra);
                return new View("resource", true);
            } catch (ServiceException e) {
                throw new ActionException("Could not add Author for Resource", e);
            }
        } else {
            throw new IllegalArgumentException("Incorrect values");
        }
    }

    private boolean isValide(String authorId, String resourceId) {
        return (ActionUtil.isValide(authorId, Validator.NOT_EMPTY_NUMBER) &&
                ActionUtil.isValide(resourceId, Validator.NOT_EMPTY_NUMBER));
    }
}
