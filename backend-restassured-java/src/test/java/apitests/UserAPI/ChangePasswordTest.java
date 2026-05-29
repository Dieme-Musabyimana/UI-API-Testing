package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.services.UserService;
import api.utils.Expectations;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BaseAPI {
    UserService userService;
    String token;
    Response response;

    @BeforeMethod
    public void setUp(){
        this.userService = new UserService();
        this.token = userService.login2();

    }

    @Test
    public void changePasswordTest(){
        Response response = userService.changePassword(token);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expectations.PASSWORD_CHANGED);
    }

    @Test
    public void changePasswordWithoutLogin(){
        Response response = userService.changePassword("");
        Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expectations.AUTHENTICATION_ERROR);
    }

    @Test
    public void changePasswordWithWrongPassword(){

    }

}
