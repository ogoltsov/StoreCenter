package com.epam.ok.storeCenter.action;


import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private static final Map<String, Action> actions = new HashMap<>();
    static {
        actions.put("GET/login", new GetPage("login"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new GetPage("register"));
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/cabinet", new GetPage("cabinet"));
        actions.put("GET/resource", new GetResourceAction());
        actions.put("GET/status", new GetStatusAction());
        actions.put("GET/category", new GetCategoryAction());
        actions.put("GET/author", new GetAuthorAction());
        actions.put("GET/speciality", new GetSpecialityAction());
        actions.put("GET/user", new GetUserAction());
    }

    public static Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
