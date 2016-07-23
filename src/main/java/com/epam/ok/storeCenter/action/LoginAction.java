package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.User;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class LoginAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService service = new UserService();
        User user;
        ActionResult result;
        try {
            user = service.performUserLogin(email, password);

            if (user != null) {
                request.getSession(false).setAttribute("loggedUser", user);
                result = new ActionResult("cabinet");
                result.setRedirect(true);
            } else {
                request.setAttribute("loginError", "Invalid Login or Password");
                result = new ActionResult("login");
            }
        } catch (ServiceException e) {
            throw new ActionException();
        }
        return result;
    }
}
