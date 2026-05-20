package uitests.Auth;

import base.BaseTest;
import flows.AuthFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    AuthFlow flow;

    @Test
    public void registerTest(){
        flow = new AuthFlow(pages);
        flow.register();
        Assert.assertTrue(page.url().contains("home"));
    }

}
