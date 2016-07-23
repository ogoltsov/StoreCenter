package com.epam.ok.storeCenter.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        request.getSession(false).removeAttribute("loggedUser");

        return new ActionResult("login", true);
    }
}
