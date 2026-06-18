package apitests.AuthAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseAPI {

    @Test
    public void forgotPasswordTest (){
        AuthService authService = new AuthService();
        Response response= authService.requestForgotPasswordEmail();
        boolean successValue =response.jsonPath().getBoolean("success");
        String messageValue =response.jsonPath().getString("message");

        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(successValue);
        Assert.assertEquals(messageValue, Expected.successMessage);
    }
}
