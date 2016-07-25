package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class DeleteUserAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String id = request.getParameter("id");

        UserService service = new UserService();
        try {
            service.deleteUser(Integer.parseInt(id));
            return new View("user", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not delete User, id: " + id, e);
        }

    }
}
