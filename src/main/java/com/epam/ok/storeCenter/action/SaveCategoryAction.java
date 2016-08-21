package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Category;
import com.epam.ok.storeCenter.service.CategoryService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class SaveCategoryAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        CategoryService service = new CategoryService();
        Category category = new Category();

        category.setId("".equals(id) ? null : Integer.parseInt(id));
        category.setTitle(title);
        category.setDescription(description);

        try {
            Category foundedCategory = service.getByTitle(title);
            if ((foundedCategory == null) || (foundedCategory.getId() == category.getId())) {
                service.save(category);
                return new View("category", true);
            } else {
                request.setAttribute("category", category);
                request.setAttribute("error", "Check fields");
                return new View("category");
            }
        } catch (ServiceException e) {
            throw new ActionException("Could not save Category", e);
        }
    }
}
