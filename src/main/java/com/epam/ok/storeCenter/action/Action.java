package com.epam.ok.storeCenter.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException;
}
