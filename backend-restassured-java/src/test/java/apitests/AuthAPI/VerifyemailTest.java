package apitests.AuthAPI;

import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.payloads.RequestPayloads;
import api.services.AuthService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyemailTest extends BaseAPI {
    @Test
    public void verifyEmailTest () {
        AuthService authService = new AuthService();
        Response verificationResponse = authService.registerAndVerifyEmail();
        Assert.assertEquals(verificationResponse.getStatusCode(), StatusCodes.OK);

    }

}
