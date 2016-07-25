package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.User;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class RegisterAction implements Action {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        UserService service = new UserService();
        User user = getUserFromRequest(request);
        User registeredUser;
        View result;

        try {

            boolean isEmailFree = service.checkEmail(user.getEmail());

            if (!isEmailFree) {
                result = forwardToRegisterFormWithError(request, user);
            } else {
                registeredUser = service.registerUser(user);
                request.getSession().setAttribute("loggedUser", registeredUser);
                result = new View("cabinet", true);
            }

        } catch (ServiceException e) {
            throw new ActionException("Could not register User", e);
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

    private View forwardToRegisterFormWithError(HttpServletRequest request, User user) {
        request.setAttribute("error", "You input incorrect data, try again");
        request.setAttribute("userData", user);
        request.setAttribute("emailError", "This Email registered!");
        return new View("register");
    }
}
