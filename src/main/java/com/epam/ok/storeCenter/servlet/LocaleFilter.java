package com.epam.ok.storeCenter.servlet;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("locale".equals(cookie.getName()))  {
                    Locale locale = new Locale(cookie.getValue());
                    request.getSession().setAttribute("locale", locale);
                    Config.set(request.getSession(), Config.FMT_LOCALE, locale);
                }

//                if (request.getSession(false).getAttribute("locale") == null) {
//                    request.getSession().setAttribute("locale", request.getLocale());
////                    logger.debug("Default locale ({}) added in session", request.getLocale());
//                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
