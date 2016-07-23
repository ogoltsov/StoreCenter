package com.epam.ok.storeCenter.servlet;

import com.epam.ok.storeCenter.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter
public class SecurityFilter implements Filter {

    private static List<String> guestViews;
    private static List<String> userViews;
    private static List<String> moderViews;
    private static List<String> adminViews;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        guestViews = new ArrayList<>();
        guestViews.add("/login");
        guestViews.add("/register");

        userViews = new ArrayList<>();
        userViews.add("/resource");
//        userViews.add("/resources");
        userViews.add("/author");
        userViews.add("/cabinet");
        userViews.add("/logout");

        moderViews = new ArrayList<>(userViews);
        moderViews.add("/category");
        moderViews.add("/college");
        moderViews.add("/department");
        moderViews.add("/speciality");
        moderViews.add("/status");

        adminViews = new ArrayList<>(moderViews);
        adminViews.add("/user");
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
                try {
                    response.sendRedirect(request.getContextPath() + "/app/login");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    }
}
