package com.epam.ok.storeCenter.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class GetPage implements Action {

    private ActionResult result;

    GetPage(String pagename) {
        this.result = new ActionResult(pagename);
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        return result;
    }
}
