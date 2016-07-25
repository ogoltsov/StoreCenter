package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CreateUserAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        request.setAttribute("roles", User.Role.getRoles());
        return new View("user");
    }
}
