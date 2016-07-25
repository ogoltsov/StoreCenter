package com.epam.ok.storeCenter.action;


import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private static final Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/login", new GetPage("login"));
        actions.put("GET/author", new GetAuthorAction());
        actions.put("GET/status", new GetStatusAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/cabinet", new GetPage("cabinet"));
        actions.put("GET/register", new GetPage("register"));
        actions.put("GET/resource", new GetResourceAction());
        actions.put("GET/category", new GetCategoryAction());
        actions.put("GET/speciality", new GetSpecialityAction());
        actions.put("GET/user", new GetUserAction());
        actions.put("POST/saveUser", new SaveUserAction());
        actions.put("GET/createUser", new CreateUserAction());
        actions.put("POST/deleteUser", new DeleteUserAction());
        actions.put("POST/saveSpeciality", new SaveSpecialityAction());
        actions.put("GET/createSpeciality", new CreateSpecialityAction());
        actions.put("POST/deleteSpeciality", new DeleteSpecialityAction());
        actions.put("POST/saveCategory", new SaveCategoryAction());
        actions.put("GET/createCategory", new CreateCategoryAction());
        actions.put("POST/deleteCategory", new DeleteCategoryAction());
        actions.put("POST/saveStatus", new SaveStatusAction());
        actions.put("GET/createStatus", new CreateStatusAction());
        actions.put("POST/deleteStatus", new DeleteStatusAction());
        actions.put("POST/saveAuthor", new SaveAuthorAction());
        actions.put("GET/createAuthor", new CreateAuthorAction());
        actions.put("POST/deleteAuthor", new DeleteAuthorAction());
        actions.put("POST/saveResource", new SaveResourceAction());
        actions.put("GET/createResource", new CreateResourceAction());
        actions.put("POST/deleteResource", new DeleteResourceAction());
        actions.put("POST/setAuthorForResource", new AddAuthorForResourceAction());
        actions.put("GET/removeAuthorFromResource", new RemoveAuthorFromResourceAction());
        actions.put("POST/setSpecialityForResource", new AddSpecialityForResourceAction());
        actions.put("GET/removeSpecialityFromResource", new RemoveSpecialityFromResourceAction());
    }

    public static Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
