package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Speciality;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class GetSpecialityAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        View result;

        String id = request.getParameter("id");

        SpecialityService service = new SpecialityService();
        if (id == null) {
            try {
                List<Speciality> specialityList = service.getAll();
                request.setAttribute("specialityList", specialityList);
                result = new View("specialityList");
            } catch (ServiceException e) {
                throw new ActionException("Could not get Speciality list", e);
            }
        } else {
            Speciality speciality;
            try {
                speciality = service.getByPK(Integer.parseInt(id));
            } catch (ServiceException e) {
                throw new ActionException("Could not get Speciality, id: " + id, e);
            }
            request.setAttribute("speciality", speciality);
            result = new View("speciality");
        }
        return result;
    }
}
