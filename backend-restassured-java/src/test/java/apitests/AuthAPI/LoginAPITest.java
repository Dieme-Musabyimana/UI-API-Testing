package apitests.AuthAPI;

import api.POJOs.responsePOJO.registerResPOJO.LoginResPOJO;
import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import api.utils.Config;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAPITest extends BaseAPI {

    @Test
    public void loginTest (){
   AuthService authService = new AuthService();
   Response response = authService.login();
   LoginResPOJO loginResBody = response.getBody().as(LoginResPOJO.class);

        Assert.assertEquals(response.getStatusCode(), Status.OK, "User registration failed!");
        Assert.assertTrue(loginResBody.isSuccess(), "Success key flag should be true");
        Assert.assertEquals(loginResBody.getMessage(), "Login successful");

        Assert.assertNotNull(loginResBody.getData().getToken());
        Assert.assertNotNull(loginResBody.getData().getRefreshToken());
        Assert.assertEquals(loginResBody.getData().getUser().getFirstName(), Expected.firstname);
        Assert.assertEquals(loginResBody.getData().getUser().getLastName(), Expected.lastname);
        Assert.assertEquals(loginResBody.getData().getUser().getRole(), "ADMIN");
        Assert.assertEquals(loginResBody.getData().getUser().getEmail(), Config.getLoginEmail());
        Assert.assertNotNull(loginResBody.getData().getUser().getPhone());
        Assert.assertTrue(loginResBody.getData().getUser().isVerified());
        Assert.assertNotNull(loginResBody.getData().getUser().getId());


    }
}