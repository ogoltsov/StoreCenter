package com.epam.ok.storeCenter.action;

public class ActionException extends Exception {

    ActionException() {}

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
