package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.Validator;
import com.epam.ok.storeCenter.model.User;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class GetUserAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        View result;
        String id = request.getParameter("id");

        UserService service = new UserService();
        try {
            if (id == null) {
                List<User> userList = service.getAll();
                request.setAttribute("userList", userList);
                result = new View("userList");
            } else {
                if (!isValid(id)) {
                    throw new IllegalArgumentException("Illegal argument");
                } else {
                    User user = service.getByPK(Integer.parseInt(id));
                    request.setAttribute("user", user);
                    request.setAttribute("roles", User.Role.getRoles());
                    result = new View("user");
                }
            }
        } catch (ServiceException e) {
            throw new ActionException("Could not execute GetUserAction", e);
        }

        return result;
    }

    private boolean isValid(String id) {
        return ActionUtil.isValide(id, Validator.NOT_EMPTY_NUMBER);
    }
}
