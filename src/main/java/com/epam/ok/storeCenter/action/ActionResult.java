package com.epam.ok.storeCenter.action;

public class ActionResult {

    private String view;
    private boolean isRedirect;

    ActionResult(String pageName) {
        this.view = pageName;
        this.isRedirect = false;
    }

    ActionResult(String pageName, boolean isRedirect) {
        this.view = pageName;
        this.isRedirect = isRedirect;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

}
