package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.UserService;
import api.utils.Expected;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetUserAddress extends BaseAPI {
    TokenManager tokenManager;
    UserService userService;

@BeforeMethod
public void setUp(){
    this.tokenManager = new TokenManager();
    this.userService = new UserService();

}
@Test
public void getUserAddressTest(){
    String token = tokenManager.getToken();
    Response response = userService.getUserAddress(token);
    Assert.assertEquals(response.getStatusCode(), Status.OK);
    Assert.assertTrue(response.jsonPath().getBoolean("success"));
    }
    @Test
    public void getUserAddressWithoutLogin(){
    Response response = userService.getUserAddress(" ");
    Assert.assertEquals(response.getStatusCode(), Status.UNAUTHORIZED);
    Assert.assertFalse(response.jsonPath().getBoolean("success"));
    Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
}
}
