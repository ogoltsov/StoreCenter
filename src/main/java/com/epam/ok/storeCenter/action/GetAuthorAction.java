package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Author;
import com.epam.ok.storeCenter.service.AuthorService;
import com.epam.ok.storeCenter.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAuthorAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ActionResult result;
        String id = request.getParameter("id");
        if (id == null) {
            AuthorService service = new AuthorService();
            try {
                List<Author> authorList = service.getAll();
                request.setAttribute("authorList", authorList);
                result = new ActionResult("authorList");
            } catch (ServiceException e) {
                throw new ActionException();
            }
        } else {
            return null;
        }
        return result;
    }
}
