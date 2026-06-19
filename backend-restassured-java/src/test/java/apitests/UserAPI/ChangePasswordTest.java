package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.UserService;
import api.utils.Expected;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BaseAPI {
    UserService userService;
    @BeforeMethod
    public void setUserService(){
        this.userService = new UserService();
    }

    @Test
    public void changePasswordTest(){
        Response response = userService.changePassword(LoginAs.ADMIN);
        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.PASSWORD_CHANGED);
    }

    @Test
    public void changePasswordWithoutLogin(){
        Response response = userService.changePassword(LoginAs.ADMIN);
        Assert.assertEquals(response.getStatusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
    }

}
