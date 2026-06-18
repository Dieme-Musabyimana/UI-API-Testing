package apitests.AuthAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyemailTest extends BaseAPI {
    @Test
    public void verifyEmailTest () {
        AuthService authService = new AuthService();
        Response verificationResponse = authService.registerAndVerifyEmail();
        Assert.assertEquals(verificationResponse.getStatusCode(), Status.OK);
    }

}
