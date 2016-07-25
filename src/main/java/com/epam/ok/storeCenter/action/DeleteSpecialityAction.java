package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class DeleteSpecialityAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String id = request.getParameter("id");


        SpecialityService service = new SpecialityService();
        try {
            boolean canDelete = service.canDeleteSpecialityById(Integer.parseInt(id));

            if (canDelete) {
                service.deleteById(Integer.parseInt(id));
                return new View("speciality", true);
            } else {
                request.setAttribute("deleteError", "You can't delete this");
                request.setAttribute("id", id);
                request.setAttribute("speciality",service.getByPK(Integer.parseInt(id)));
                return new View("speciality");
            }

        } catch (ServiceException e) {
            throw new ActionException("Could not delete Speciality, id: "+ id, e);
        }

    }
}
