package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.*;
import com.epam.ok.storeCenter.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class GetResourceAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        View result;

        String id = request.getParameter("id");

        ResourceService service = new ResourceService();
        if (id == null) {
            try {
                List<Resource> resources = service.getAll();
                request.setAttribute("resources", resources);
            } catch (ServiceException e) {
                throw new ActionException("Could not get all Resource", e);
            }
            result = new View("resourceList");
        } else {

            try {
                Resource resource = service.getByPK(Integer.parseInt(id));

                StatusService statusService = new StatusService();
                CategoryService categoryService = new CategoryService();
                AuthorService authorService = new AuthorService();
                SpecialityService specialityService = new SpecialityService();

                List<Speciality> specialityList = specialityService.getAll();
                List<Author> authorList = authorService.getAll();
                List<Status> statusList = statusService.getAll();
                List<Category> categoryList = categoryService.getAll();

                specialityList.removeAll(resource.getSpecialities());
                authorList.removeAll(resource.getAuthors());

                request.setAttribute("categoryList", categoryList);
                request.setAttribute("statusList", statusList);
                request.setAttribute("specialityList", specialityList);
                request.setAttribute("authorList", authorList);
                request.setAttribute("resource", resource);

                result = new View("resource");
            } catch (ServiceException e) {
                throw new ActionException("Could not get Resource, id: " + id, e);
            }
        }

        return result;
    }

}
