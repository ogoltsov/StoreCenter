package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Author;
import com.epam.ok.storeCenter.service.AuthorService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

class SaveAuthorAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String id = request.getParameter("id");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String patronymic = request.getParameter("patronymic");
        Author author = new Author();

        author.setId(id.equals("") ? null : Integer.parseInt(id));
        author.setLastname(lastname);
        author.setPatronymic(patronymic);
        author.setFirstname(firstname);


        AuthorService service = new AuthorService();

        try {
            Author foundedAuthor = service.findByFIO(lastname, firstname, patronymic);
            if ((foundedAuthor == null) || Objects.equals(foundedAuthor.getId(), author.getId())) {
                service.save(author);
                return new View("author", true);
            } else {
                return forwardBackWithError(request, author);
            }

        } catch (ServiceException e) {
            throw new ActionException("Could not save Author", e);
        }
    }


    private View forwardBackWithError(HttpServletRequest request, Author author) {
        request.setAttribute("author", author);
        request.setAttribute("error", "Check fields :)");
        return new View("author");
    }
}
