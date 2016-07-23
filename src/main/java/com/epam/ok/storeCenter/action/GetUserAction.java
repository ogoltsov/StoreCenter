package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.User;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetUserAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ActionResult result;
        String id = request.getParameter("id");
        UserService service = new UserService();

        if (id == null) {
            List<User> userList = service.getAll();
            request.setAttribute("userList", userList);
            result = new ActionResult("userList");

        } else {
            try {
                User user = service.getByPK(Integer.parseInt(id));

                request.setAttribute("user", user);
                request.setAttribute("roles", User.Role.getRoles());
                result = new ActionResult("user");

            } catch (ServiceException e) {
                throw new ActionException();
            }


        }

        return result;
    }
}
