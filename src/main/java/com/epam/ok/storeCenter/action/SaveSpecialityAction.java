package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Speciality;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class SaveSpecialityAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String title = request.getParameter("title");

        try {
            Speciality speciality = new Speciality();


            speciality.setId(id.equals("") ? null : Integer.parseInt(id));
            speciality.setTitle(title);
            speciality.setCode(code);


            SpecialityService service = new SpecialityService();
            service.save(speciality);

            return new View("speciality", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not save Speciality", e);
        }
    }


}
