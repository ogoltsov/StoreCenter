package com.epam.ok.storeCenter.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CreateAuthorAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        return new View("author");
    }
}
