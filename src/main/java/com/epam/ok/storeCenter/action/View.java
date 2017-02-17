package com.epam.ok.storeCenter.action;

public class View {

    private String view;
    private boolean isRedirect;

    public View(String pageName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        View view1 = (View) o;

        if (isRedirect != view1.isRedirect) return false;
        return view.equals(view1.view);
    }

    @Override
    public int hashCode() {
        int result = view.hashCode();
        result = 31 * result + (isRedirect ? 1 : 0);
        return result;
    }
}
