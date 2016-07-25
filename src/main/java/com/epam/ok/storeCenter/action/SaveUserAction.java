package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.Validator;
import com.epam.ok.storeCenter.model.User;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class SaveUserAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        View result;

        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String role = request.getParameter("role");

        User user = new User();

        user.setId(id.equals("") ? null : Integer.parseInt(id));
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role));
        if (isValid(id, Validator.NOT_EMPTY_NUMBER) && isValid(email, Validator.EMAIL) && isValid(password, Validator.PASSWORD)
                && isValid(lastname, Validator.NOT_EMPTY_TEXT) && isValid(firstname, Validator.NOT_EMPTY_TEXT)) {
            UserService service = new UserService();
            try {
                if (service.checkEmail(email)) {
                    service.save(user);
                    result = new View("user", true);
                } else {
                    request.setAttribute("error", "Incorrect fields");
                    request.setAttribute("user", user);
                    return new View("user");
                }
            } catch (ServiceException e) {
                throw new ActionException("Could not save User", e);
            }
            return result;
        } else {
            request.setAttribute("user", user);
            request.setAttribute("error", "Incorrect fields");
            return new View("user");
        }


    }

    private boolean isValid(String param, String regEx) {
        Validator validator = new Validator();
        return validator.validate(param, regEx);
    }
}
