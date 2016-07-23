package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.User;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class RegisterAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        UserService service = new UserService();
        User user = getUserFromRequest(request);
        User registeredUser;
        ActionResult result;
        try {
            boolean isEmailFree = service.checkEmail(request.getParameter("email"));
            if (!isEmailFree) {
                request.setAttribute("emailError", "used");
                registeredUser = null;
            } else {
                registeredUser = service.registerUser(user);
            }
            if (registeredUser != null) {
                request.getSession().setAttribute("loggedUser", registeredUser);
                result = new ActionResult("cabinet", true);
            } else {
                request.setAttribute("error", "You input incorrect data, try again");
                request.setAttribute("userData", user);
                result = new ActionResult("register");
            }
        } catch (ServiceException e) {
            throw new ActionException();
        }
        return result;
    }

    private User getUserFromRequest(HttpServletRequest request) {
        User user = new User();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        user.setEmail(email);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);

        return user;
    }
}
