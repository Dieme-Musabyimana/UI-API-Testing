package uitests.Auth;

import base.BaseTest;
import constants.Data;
import flows.AuthFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class logoutTest extends BaseTest {
AuthFlow flow;

@Test
public void logoutTest(){
    flow = new AuthFlow(pages);
    flow.login().signOut();
    Assert.assertEquals(page.url(), Data.URL);

}
}
