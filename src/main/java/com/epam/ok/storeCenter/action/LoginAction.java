package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.User;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class LoginAction implements Action {
    private static final Logger logger = Logger.getLogger(LoginAction.class);

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserService service = new UserService();
        User user;
        View result;

        try {

            user = service.performUserLogin(email, password);
            if (user != null) {
                request.getSession(false).setAttribute("loggedUser", user);
                result = new View("cabinet");
                result.setRedirect(true);
                logger.info("User is logged on: " + user.toString());
            } else {
                result = returnToLoginViewWithError(request);
            }

        } catch (ServiceException e) {
            throw new ActionException("Could not execute LoginAction", e);
        }
        return result;
    }


    private View returnToLoginViewWithError(HttpServletRequest request) {
        request.setAttribute("loginError", "Invalid Login or Password");
        return new View("login");
    }
}
