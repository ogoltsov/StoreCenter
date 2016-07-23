package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Resource;
import com.epam.ok.storeCenter.service.ResourceService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class GetResourceAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ActionResult result;

        String id = request.getParameter("id");
        ResourceService service = new ResourceService();
        if (id == null) {
            try {
                List<Resource> resources = service.getAll();
                request.setAttribute("resources", resources);
            } catch (ServiceException e) {
                throw new ActionException();
            }
            result = new ActionResult("resources");
        } else {

            result = new ActionResult("resource");
        }

        return result;
    }
}
