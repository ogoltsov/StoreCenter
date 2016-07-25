package com.epam.ok.storeCenter.servlet;

import com.epam.ok.storeCenter.action.Action;
import com.epam.ok.storeCenter.action.ActionException;
import com.epam.ok.storeCenter.action.ActionFactory;
import com.epam.ok.storeCenter.action.View;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(FrontControllerServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getMethod() + req.getPathInfo();
        Action action = ActionFactory.getAction(actionName);
        View view;
        try {
            view = action.execute(req, resp);
        } catch (ActionException e) {
            throw new ServletException("Could not execute: " + actionName, e);
        }
        forward(view, req, resp);
    }

    private void forward(View view, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Forward user: " + view.getView());
        if (view.isRedirect()) {
            String location = request.getContextPath() + "/app/" + view.getView();
            response.sendRedirect(location);
        } else {
            String path = "/WEB-INF/jsp/" + view.getView() + ".jsp";
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
