package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class LogoutAction implements Action {
    private static final Logger logger = Logger.getLogger(LogoutAction.class);

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        User loggedUser = (User) request.getAttribute("loggedUser");
        request.getSession(false).removeAttribute("loggedUser");
        logger.info("User is logout: " + loggedUser.toString());
        return new View("login", true);
    }
}
