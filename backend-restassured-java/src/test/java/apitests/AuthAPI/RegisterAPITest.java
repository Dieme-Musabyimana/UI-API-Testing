package apitests.AuthAPI;

import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.POJOs.responsePOJO.registerResPOJO.RegisterResPOJO; // Import your response root
import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.payloads.RequestPayloads;
import api.services.AuthService;
import api.utils.FakerUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterAPITest extends BaseAPI {

    @Test
    public void RegisterTest(){
        RegisterReqPOJO reqBody = RequestPayloads.createReqBody();
        AuthService auth = new AuthService();
        Response response = auth.registerUser(reqBody);
        RegisterResPOJO resBody = response.getBody().as(RegisterResPOJO.class);
        System.out.println("<<<<<<<<<<<<<<<<<<<<" + FakerUtils.getPassword());
        System.out.println("<<<<<<<<<<<<<<<<<<<<" + FakerUtils.getEmail());



        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED, "User registration failed!");
        Assert.assertTrue(resBody.isSuccess(), "Success key flag should be true");
        Assert.assertEquals(resBody.getMessage(), "Registration successful. Check your email to verify.");

        Assert.assertNotNull(resBody.getData().getToken(), "Access token is missing!");
        Assert.assertNotNull(resBody.getData().getRefreshToken(), "Refresh token is missing!");
        Assert.assertEquals(resBody.getData().getUser().getFirstName(), reqBody.getFirstName(), "First name mismatch!");
        Assert.assertEquals(resBody.getData().getUser().getLastName(), reqBody.getLastName(), "Last name mismatch!");
        Assert.assertEquals(resBody.getData().getUser().getEmail(), reqBody.getEmail(), "Email mismatch!");
        Assert.assertEquals(resBody.getData().getUser().getRole(), "CUSTOMER");
        Assert.assertNotNull(resBody.getData().getUser().getId(), "Server didn't generate a User ID");
    }
}