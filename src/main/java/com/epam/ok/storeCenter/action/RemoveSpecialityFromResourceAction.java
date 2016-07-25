package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.ResourceSpeciality;
import com.epam.ok.storeCenter.service.ResourceService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class RemoveSpecialityFromResourceAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String resId = request.getParameter("resId");
        String specId = request.getParameter("specId");

        ResourceService service = new ResourceService();
        ResourceSpeciality rs = new ResourceSpeciality();
        rs.setResourceId(Integer.parseInt(resId));
        rs.setSpecialityId(Integer.parseInt(specId));

        try {
            service.removeSpecialityFromResource(rs);
            return new View("resource", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not remove Speciality from Resource", e);
        }
    }
}
