package com.epam.ok.storeCenter.servlet;

import com.epam.ok.storeCenter.pool.ConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class AppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        DataSource pool = ConnectionPool.getInstance();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("pool", pool);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ConnectionPool pool = (ConnectionPool) servletContext.getAttribute("pool");
        pool.close();

    }
}
