package apitests.AuthAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseAPI {

    private AuthService authService;

    @BeforeClass
    public void setup() {
        authService = new AuthService();
    }

    @Test(description = "Reset password with a valid token")
    public void shouldResetPasswordWithValidToken() {
        String token = new TokenManager().getAllTokens().getFirst();

        Response response = authService.resetPassword(token);

        Assert.assertEquals(response.getStatusCode(), Status.OK);
    }

    @Test(description = "Reset password with an invalid token")
    public void shouldFailToResetPasswordWithInvalidToken() {
        Response response = authService.resetPassword("invalid-token");

        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }

    @Test(description = "Reset password with an empty token")
    public void shouldFailToResetPasswordWithEmptyToken() {
        Response response = authService.resetPassword("");

        Assert.assertEquals(response.getStatusCode(), Status.NOT_FOUND);
    }


    @Test(description = "Reset password with a malformed token")
    public void shouldFailToResetPasswordWithInvalidTokenFormat() {
        Response response = authService.resetPassword("@@@###$$$");

        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }

    @Test(description = "Reset password with a whitespace token")
    public void shouldFailToResetPasswordWithWhitespaceToken() {
        Response response = authService.resetPassword(" ");

        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }

}