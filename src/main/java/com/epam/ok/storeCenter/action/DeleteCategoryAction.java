package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.Category;
import com.epam.ok.storeCenter.service.CategoryService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class DeleteCategoryAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String id = request.getParameter("id");


        CategoryService service = new CategoryService();
        try {
            boolean canDelete = service.canDelete(Integer.parseInt(id));
            if (canDelete) {
                try {
                    service.delete(Integer.parseInt(id));
                    return new View("category", true);
                } catch (DaoException e) {
                    throw new ActionException("Can't execute action " + this.getClass().getName());
                }
            } else {
                Category category = new Category();
                category.setId(Integer.parseInt(id));
                category.setTitle(request.getParameter("title"));
                category.setDescription(request.getParameter("description"));

                request.setAttribute("error", "Sorry, You can't delete this :(");
                request.setAttribute("category", category);
                return new View("category");
            }
        } catch (ServiceException e) {
            throw new ActionException("Could not delete Category, id: " + id, e);
        }

    }
}
