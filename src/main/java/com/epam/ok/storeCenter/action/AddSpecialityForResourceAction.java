package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.ResourceSpeciality;
import com.epam.ok.storeCenter.service.ResourceService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class AddSpecialityForResourceAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String specId = request.getParameter("specId");
        String resourceId = request.getParameter("resourceId");

        ResourceService service = new ResourceService();
        ResourceSpeciality rs = new ResourceSpeciality();
        rs.setResourceId(Integer.parseInt(resourceId));
        rs.setSpecialityId(Integer.parseInt(specId));
        try {
            service.addSpecialityForResource(rs);
            return new View("resource", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not add Speciality", e);
        }

    }


}
