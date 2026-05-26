package apitests.AuthAPI;

import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.services.AuthService;
import api.utils.Expectations;
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

        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        Assert.assertTrue(successValue);
        Assert.assertEquals(messageValue, Expectations.successMessage);
    }
}
