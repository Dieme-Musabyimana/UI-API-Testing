package uitests.Auth;

import base.BaseTest;
import constants.Data;
import flows.AuthFlow;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    AuthFlow flow;

    @BeforeMethod
    public void setUpFlow(){
        this.flow = new AuthFlow(pages);
    }

    @Test
    public void registerTest(){
        flow.register();
        Assert.assertTrue(page.url().contains("home"));
    }
    @Test
    public void registerWithEmptyFldTest(){
        pages.getMainPage().goToRegisterPage().registerWithEmptyFld();
        Assert.assertEquals(pages.getRegisterPage().getErrorMsg(), Data.emptyFldErrMsg);
        System.out.println(pages.getRegisterPage().getErrorMsg());
    }
    @Test
    public void registerWithInvEmail(){
        pages.getMainPage().goToRegisterPage().registerWithInvEml();
    }

}
