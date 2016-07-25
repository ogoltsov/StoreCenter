package com.epam.ok.storeCenter.servlet;

import com.epam.ok.storeCenter.model.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter
public class SecurityFilter implements Filter {
    private static final Logger logger = Logger.getLogger(SecurityFilter.class);
    private static List<String> guestViews;
    private static List<String> userViews;
    private static List<String> moderViews;
    private static List<String> adminViews;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("SecurityFilter init");

        guestViews = new ArrayList<>();
        guestViews.add("/login");
        guestViews.add("/register");

        userViews = new ArrayList<>();
        userViews.add("/resource");
        userViews.add("/author");
        userViews.add("/cabinet");
        userViews.add("/logout");

        moderViews = new ArrayList<>(userViews);
        moderViews.add("/category");
        moderViews.add("/college");
        moderViews.add("/department");
        moderViews.add("/speciality");
        moderViews.add("/status");
        moderViews.add("/saveSpeciality");
        moderViews.add("/createSpeciality");
        moderViews.add("/deleteSpeciality");
        moderViews.add("/saveCategory");
        moderViews.add("/createCategory");
        moderViews.add("/deleteCategory");
        moderViews.add("/saveStatus");
        moderViews.add("/createStatus");
        moderViews.add("/deleteStatus");
        moderViews.add("/saveAuthor");
        moderViews.add("/createAuthor");
        moderViews.add("/deleteAuthor");
        moderViews.add("/saveResource");
        moderViews.add("/createResource");
        moderViews.add("/deleteResource");
        moderViews.add("/setAuthorForResource");
        moderViews.add("/removeAuthorFromResource");
        moderViews.add("/setSpecialityForResource");
        moderViews.add("/removeSpecialityFromResource");

        adminViews = new ArrayList<>(moderViews);
        adminViews.add("/user");
        adminViews.add("/saveUser");
        adminViews.add("/createUser");
        adminViews.add("/deleteUser");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("loggedUser");
        String contextPath = request.getPathInfo();
        if (user == null) {
            if (!guestViews.contains(contextPath)) {
                response.sendRedirect(request.getContextPath() + "/app/login");
                return;
            }
        } else if (contextPath.startsWith("/login")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        } else {
            List<String> views = getViewsForRole(user.getRole());

            if (!views.contains(contextPath)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You haven't permissions");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private List<String> getViewsForRole(User.Role role) {
        List<String> views;
        switch (role) {
            case admin:
                views = adminViews;
                break;
            case moderator:
                views = moderViews;
                break;
            default:
                views = userViews;
                break;
        }
        return views;
    }

    @Override
    public void destroy() {
        logger.info("SecurityFilter destroyed");
    }
}
