package com.epam.ok.storeCenter.action;

import com.epam.ok.storeCenter.Validator;

public  class ActionUtil {
    public static boolean isValide(String param, String regEx) {
        Validator validator = new Validator();
        return validator.validate(param, regEx);
    }
}
