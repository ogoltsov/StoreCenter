package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Author;
import com.epam.ok.storeCenter.model.Resource;
import com.epam.ok.storeCenter.service.AuthorService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class GetAuthorAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        View result;
        String id = request.getParameter("id");

        AuthorService service = new AuthorService();
        if (id == null) {
            try {
                List<Author> authorList = service.getAll();
                request.setAttribute("authorList", authorList);
                result = new View("authorList");
            } catch (ServiceException e) {
                throw new ActionException("Could not get Author list", e);
            }
        } else {
            try {
                Author author = service.getByPK(Integer.parseInt(id));
                List<Resource> resources = service.getResourcesForAuthor(Integer.parseInt(id));

                request.setAttribute("author", author);
                request.setAttribute("resources", resources);
                result = new View("author");
            } catch (ServiceException e) {
                throw new ActionException("Could not get Author, id: " + id, e);
            }
        }

        return result;
    }

}
