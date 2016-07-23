package com.epam.ok.storeCenter.servlet;

import com.epam.ok.storeCenter.action.Action;
import com.epam.ok.storeCenter.action.ActionException;
import com.epam.ok.storeCenter.action.ActionFactory;
import com.epam.ok.storeCenter.action.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getMethod() + req.getPathInfo();
        Action action = ActionFactory.getAction(actionName);
        ActionResult actionResult;
        try {
            actionResult = action.execute(req, resp);
        } catch (ActionException e) {
            throw new ServletException();
        }
        forward(actionResult, req, resp);
    }

    private void forward(ActionResult result, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (result.isRedirect()) {
            String location = request.getContextPath() + "/app/" + result.getView();
            response.sendRedirect(location);
        } else {
            String path = "/WEB-INF/jsp/" + result.getView() + ".jsp";
            request.getRequestDispatcher(path).forward(request, response);
        }

    }
}
