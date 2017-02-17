package com.epam.action;

import com.epam.ok.storeCenter.action.Action;
import com.epam.ok.storeCenter.action.ActionException;
import com.epam.ok.storeCenter.action.ActionFactory;
import com.epam.ok.storeCenter.action.View;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPageActionTest extends ActionTestSute {

    @Test
    public void getCabinetPageAction() throws ActionException {
        Action action = ActionFactory.getAction(CABINET_PAGE);
        View actualView = action.execute(REQUEST, RESPONSE);
        View expectedView = new View("cabinet");
        Assert.assertEquals(actualView, expectedView);
    }

    @Test
    public void getLoginPageAction() throws ActionException {
        Action action = ActionFactory.getAction(LOGIN_PAGE);
        View actualView = action.execute(REQUEST, RESPONSE);
        View expectedView = new View("login1");

        Assert.assertNotEquals(actualView, expectedView);


    }

}
