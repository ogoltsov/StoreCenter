package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.Validator;
import com.epam.ok.storeCenter.model.Author;
import com.epam.ok.storeCenter.model.Resource;
import com.epam.ok.storeCenter.service.AuthorService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class DeleteAuthorAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String id = request.getParameter("id");

        AuthorService service = new AuthorService();
        if (!isValid(id)) {
            throw new IllegalArgumentException("Illegal argument");
        } else {
            try {
                boolean canDelete = service.canDelete(Integer.parseInt(id));
                if (canDelete) {
                    service.delete(Integer.parseInt(id));
                    return new View("author", true);
                } else {
                    Author author = new Author();
                    author.setId(Integer.parseInt(id));
                    author.setLastname(request.getParameter("lastname"));
                    author.setFirstname(request.getParameter("firstname"));
                    author.setPatronymic(request.getParameter("patronymic"));

                    List<Resource> resources = service.getResourcesForAuthor(Integer.parseInt(id));

                    request.setAttribute("author", author);
                    request.setAttribute("resources", resources);
                    request.setAttribute("error", "Sorry, You can't delete this :(");
                    return new View("author");

                }
            } catch (ServiceException e) {
                throw new ActionException("Could not delete Author, id: " + id, e);
            }
        }
    }

    private boolean isValid(String id) {
        return ActionUtil.isValide(id, Validator.NOT_EMPTY_NUMBER);
    }
}
