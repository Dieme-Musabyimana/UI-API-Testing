package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.services.AuthService;
import api.services.UserService;
import api.utils.ConfigReader;
import api.utils.Expectations;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetUserAddress extends BaseAPI {
    AuthService authService;
    UserService userService;

@BeforeMethod
public void setUp(){
    this.authService = new AuthService();
    this.userService = new UserService();

}
    @Test
    public void getUserAddressTest(){
        String token = authService.login().jsonPath().getString(ConfigReader.getTokenPath());
        Response response = userService.getUserAddress(token);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
    }

    @Test
    public void getUserAddressWithoutLogin(){
    Response response = userService.getUserAddress(" ");
    Assert.assertEquals(response.getStatusCode(), StatusCodes.UNAUTHORIZED);
    Assert.assertFalse(response.jsonPath().getBoolean("success"));
    Assert.assertEquals(response.jsonPath().getString("message"), Expectations.AUTHENTICATION_ERROR);
}
}
