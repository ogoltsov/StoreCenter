package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.Validator;
import com.epam.ok.storeCenter.model.Category;
import com.epam.ok.storeCenter.service.CategoryService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class GetCategoryAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        View result;
        String id = request.getParameter("id");

        CategoryService service = new CategoryService();
        if (id == null) {
            try {
                List<Category> categoryList = service.getAll();
                request.setAttribute("categoryList", categoryList);
                result = new View("categoryList");
            } catch (ServiceException e) {
                throw new ActionException("Could not get Category list", e);
            }
        } else {
            try {
                if (isValid(id)) {
                    Category category = service.getByPK(Integer.parseInt(id));
                    request.setAttribute("category", category);
                    result = new View("category");

                } else {
                    result = new View("category", true);
                }
            } catch (ServiceException e) {
                throw new ActionException("Could not get Category, id: " + id, e);
            }
        }

        return result;
    }

    private boolean isValid(String id) {
        return ActionUtil.isValide(id, Validator.NOT_EMPTY_NUMBER);
    }
}
