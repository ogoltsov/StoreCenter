package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Speciality;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetSpecialityAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ActionResult result;

        String id = request.getParameter("id");

        if (id == null) {

            SpecialityService service = new SpecialityService();
            try {
                List<Speciality> specialityList = service.getAll();
                request.setAttribute("specialityList", specialityList);
                result = new ActionResult("specialityList");
            } catch (ServiceException e) {
                throw new ActionException();
            }

        } else result = null;


        return result;
    }
}
