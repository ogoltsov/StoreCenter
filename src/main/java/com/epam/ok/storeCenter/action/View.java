package com.epam.ok.storeCenter.action;

public class View {

    private String view;
    private boolean isRedirect;

    View(String pageName) {
        this.view = pageName;
        this.isRedirect = false;
    }

    View(String pageName, boolean isRedirect) {
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
