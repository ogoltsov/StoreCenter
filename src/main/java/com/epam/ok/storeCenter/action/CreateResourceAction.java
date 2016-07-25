package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Category;
import com.epam.ok.storeCenter.model.Status;
import com.epam.ok.storeCenter.service.CategoryService;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.StatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class CreateResourceAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        StatusService statusService = new StatusService();
        CategoryService categoryService = new CategoryService();
        try {
            List<Status> statusList = statusService.getAll();
            List<Category> categoryList = categoryService.getAll();

            request.setAttribute("statusList", statusList);
            request.setAttribute("categoryList", categoryList);
            return new View("resource");
        } catch (ServiceException e) {
            throw new ActionException("Could not create Resource", e);
        }


    }
}
