package com.epam.ok.storeCenter.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class SelectLocaleAction implements Action {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String language = req.getParameter("locale");
        Config.set(req.getSession(), Config.FMT_LOCALE, new Locale(language));
        Cookie cookie = new Cookie("locale", language);
        cookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie);
//        logger.info("{} changed language to {}", req.getSession(false).getAttribute("loggedUser"), language);

        return new View(req.getHeader("referer"), true);
    }
}
