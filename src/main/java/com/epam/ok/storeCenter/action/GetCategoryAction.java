package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Category;
import com.epam.ok.storeCenter.service.CategoryService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetCategoryAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ActionResult result = null;
        String id = request.getParameter("id");
        CategoryService service = new CategoryService();
        if (id == null) {
            try {
                List<Category> categoryList = service.getAll();
                request.setAttribute("categoryList", categoryList);
                result = new ActionResult("categoryList");
            } catch (ServiceException e) {
                throw new ActionException();
            }
        } else {
            try {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } catch (IOException e) {
                throw new ActionException();
            }
        }


        return result;
    }
}
