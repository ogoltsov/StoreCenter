package com.epam.ok.storeCenter.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class LogoutAction implements Action {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        request.getSession(false).removeAttribute("loggedUser");
        return new View("login", true);
    }
}
