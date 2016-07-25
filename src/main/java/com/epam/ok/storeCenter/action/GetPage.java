package com.epam.ok.storeCenter.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class GetPage implements Action {

    private View result;

    GetPage(String pagename) {
        this.result = new View(pagename);
    }

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        return result;
    }
}
