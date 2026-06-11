package apitests.AuthAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import api.utils.Config;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetCurrentUser extends BaseAPI {
    AuthService authService;

    @BeforeMethod
    public void setUp(){
        this.authService = new AuthService();
    }

    @Test
    public void getCurrentUserTestWithoutLogin(){
        Response response = authService.getCurrentUser();
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().get("message"), "Not authenticated. Please log in.");
    }
    @Test
    public void getCurrentLoggedInUserTest(){
        Response response = authService.loginAndGetCurrentUser();
        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertEquals(response.jsonPath().get("data.user.email"), Config.getLoginEmail());
    }
}
