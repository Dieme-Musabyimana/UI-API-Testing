package uitests.Auth;

import base.BaseTest;
import flows.AuthFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    AuthFlow flow;

    @Test
    public void loginTest() {
        flow = new AuthFlow(pages);
        flow.login();
        Assert.assertTrue(page.url().contains("home"));
        Assert.assertTrue(pages.getHomePage().getSucessMsg().contains("Welcome back"));
        Assert.assertEquals(pages.getHomePage().getHomePageHeader(), "Dress forYour Story");
    }

}