package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.model.Category;
import com.epam.ok.storeCenter.model.Resource;
import com.epam.ok.storeCenter.model.Status;
import com.epam.ok.storeCenter.service.CategoryService;
import com.epam.ok.storeCenter.service.ResourceService;
import com.epam.ok.storeCenter.service.ServiceException;
import com.epam.ok.storeCenter.service.StatusService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class SaveResourceAction implements Action {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String statusId = request.getParameter("status");
        String categoryId = request.getParameter("category");
        String date = request.getParameter("date");

        ResourceService service = new ResourceService();

        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("statusId", statusId);
        params.put("typeId", categoryId);

        Resource resource = new Resource();
        try {
            resource.setId("".equals(id) ? null : Integer.parseInt(id));
            resource.setTitle(title);
            resource.setCategory(new Category(Integer.parseInt(categoryId)));
            resource.setStatus(new Status(Integer.parseInt(statusId)));
            resource.setDate(getDate(date));

            Resource foundedResource = service.getByParams(params);

            if ((foundedResource == null) || (Objects.equals(foundedResource.getId(), resource.getId()))) {
                service.save(resource);
                return new View("resource", true);
            } else {
                return forwardBackWithError(request, resource);
            }

        } catch (ServiceException e) {
            throw new ActionException("Could not save Resource", e);
        }
    }


    private View forwardBackWithError(HttpServletRequest request, Resource resource) throws ServiceException {
        StatusService statusService = new StatusService();
        CategoryService categoryService = new CategoryService();
        List<Status> statusList = statusService.getAll();
        List<Category> categoryList = categoryService.getAll();

        request.setAttribute("statusList", statusList);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("resource", resource);
        return new View("resource");
    }

    private DateTime getDate(String date) {
        String dateTime = date + " 00:00:00";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.parseDateTime(dateTime);
    }


}
