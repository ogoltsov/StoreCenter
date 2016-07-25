package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.Validator;
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
            if (isValidate(id, Validator.NOT_EMPTY_NUMBER) && isValidate(code, Validator.NOT_EMPTY_TEXT) &&
                    isValidate(title, Validator.NOT_EMPTY_TEXT)) {

                speciality.setId(id.equals("") ? null : Integer.parseInt(id));
                speciality.setTitle(title);
                speciality.setCode(code);
            } else {
                return forwardBackWithError(request, speciality);
            }

            SpecialityService service = new SpecialityService();
            service.save(speciality);
            return new View("speciality", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not save Speciality", e);
        }
    }

    private View forwardBackWithError(HttpServletRequest request, Speciality speciality) throws ServiceException {
        request.setAttribute("error", "Check your Data");
        request.setAttribute("speciality", speciality);
        return new View("resource");
    }


    private boolean isValidate(String param, String regEx) {
        Validator validator = new Validator();
        return validator.validate(param, regEx);
    }
}
