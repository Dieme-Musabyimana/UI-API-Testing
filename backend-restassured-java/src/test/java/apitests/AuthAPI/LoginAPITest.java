package apitests.AuthAPI;

import api.POJOs.responsePOJO.registerResPOJO.LoginResPOJO;
import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import api.utils.Config;
import api.utils.Expected;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAPITest extends BaseAPI {

    @Test
    public void loginTestAsAdmin (){
   AuthService authService = new AuthService();
   String email = Config.getAdminLoginEmail();
   String password = Config.getAdminLoginPassword();
   Response response = authService.login(email, password, LoginAs.NONE);
   LoginResPOJO loginResBody = response.getBody().as(LoginResPOJO.class);

        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(loginResBody.isSuccess());
        Assert.assertEquals(loginResBody.getMessage(), Expected.LOGIN_SUCCESS);

        Assert.assertNotNull(loginResBody.getData().getToken());
        Assert.assertNotNull(loginResBody.getData().getRefreshToken());
        Assert.assertEquals(loginResBody.getData().getUser().getFirstName(), Expected.firstname);
        Assert.assertEquals(loginResBody.getData().getUser().getLastName(), Expected.lastname);
        Assert.assertEquals(loginResBody.getData().getUser().getRole(), "ADMIN");
        Assert.assertEquals(loginResBody.getData().getUser().getEmail(), Config.getAdminLoginEmail());
        Assert.assertNotNull(loginResBody.getData().getUser().getPhone());
        Assert.assertTrue(loginResBody.getData().getUser().isVerified());
        Assert.assertFalse(loginResBody.getData().getUser().getId().isEmpty());
    }

    @Test
    public void loginTestAsCustomer (){
        String email = Config.getCustomerLoginEmail();
        String password = Config.getCustomerLoginPassword();
        AuthService authService = new AuthService();
        Response response = authService.login(email, password, LoginAs.CUSTOMER);
        LoginResPOJO loginResBody = response.getBody().as(LoginResPOJO.class);

        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(loginResBody.isSuccess());
        Assert.assertEquals(loginResBody.getMessage(), Expected.LOGIN_SUCCESS);

        Assert.assertNotNull(loginResBody.getData().getToken());
        Assert.assertNotNull(loginResBody.getData().getRefreshToken());
        Assert.assertEquals(loginResBody.getData().getUser().getFirstName(), "Didy");
        Assert.assertEquals(loginResBody.getData().getUser().getLastName(), "MBOKA");
        Assert.assertEquals(loginResBody.getData().getUser().getRole(), "CUSTOMER");
        Assert.assertEquals(loginResBody.getData().getUser().getEmail(), Config.getCustomerLoginEmail());
        Assert.assertNotNull(loginResBody.getData().getUser().getPhone());
        Assert.assertFalse(loginResBody.getData().getUser().isVerified());
        Assert.assertFalse(loginResBody.getData().getUser().getId().isEmpty());
    }

    @Test
    public void loginWithWrongPassword(){
    String email = Config.getCustomerLoginEmail();
    String password ="12345";
        AuthService authService = new AuthService();
        Response response = authService.login(email, password, LoginAs.CUSTOMER);
        LoginResPOJO loginResBody = response.getBody().as(LoginResPOJO.class);

        Assert.assertEquals(response.getStatusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(loginResBody.isSuccess());
        Assert.assertEquals(loginResBody.getMessage(), Expected.INVALID_CREDENTIALS);
        Assert.assertNull(loginResBody.getData());
    }

    @Test
    public void loginWithEmptyPasswordField(){
        String email = Config.getCustomerLoginEmail();
        String password = " ";
        AuthService authService = new AuthService();
        Response response = authService.login(email, password, LoginAs.CUSTOMER);
        LoginResPOJO loginResBody = response.getBody().as(LoginResPOJO.class);

        Assert.assertEquals(response.getStatusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(loginResBody.isSuccess());
        Assert.assertEquals(loginResBody.getMessage(), Expected.INVALID_CREDENTIALS);
        Assert.assertNull(loginResBody.getData());
    }

    @Test
    public void loginWithEmptyEmail(){
        String email = " ";
        String password = Config.getCustomerLoginEmail();
        AuthService authService = new AuthService();
        Response response = authService.login(email, password, LoginAs.CUSTOMER);
        LoginResPOJO loginResBody = response.getBody().as(LoginResPOJO.class);

        Assert.assertEquals(response.getStatusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(loginResBody.isSuccess());
        Assert.assertEquals(loginResBody.getMessage(), Expected.INVALID_CREDENTIALS);
        Assert.assertNull(loginResBody.getData());
    }

}