package apitests.AuthAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import api.utils.Config;
import api.utils.Message;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RefreshTokenAPITest extends BaseAPI {

    private AuthService authService;
    private TokenManager tokenManager;

    @BeforeMethod
    public void setUp() {
        this.authService = new AuthService();
        this.tokenManager = new TokenManager();
    }

    @Test(description = "Verify requesting an access token with a valid, active refresh token succeeds")
    public void getRefreshToken_ValidToken_ReturnsNewTokens() {
        String validRefreshToken = tokenManager.getAllTokens().getLast();
        Response response = authService.getRefreshToken(validRefreshToken);

        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertNotNull(response.jsonPath().getString("data.token"));
    }

    @Test(description = "Verify requesting an access token with an invalid/malformed refresh token is rejected")
    public void getRefreshToken_WithInvalidToken_ReturnsUnauthorized() {
        String invalidRefreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.invalid_payload_signature";

        Response response = authService.getRefreshToken(invalidRefreshToken);
        Assert.assertEquals(response.getStatusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }

    @Test(description = "Verify requesting an access token with an empty refresh token string is rejected")
    public void getRefreshToken_WithEmptyToken_ReturnsBadRequest() {
        Response response = authService.getRefreshToken("");

        Assert.assertTrue(
                response.getStatusCode() == Status.BAD_REQUEST || response.getStatusCode() == Status.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }

    @Test(description = "Verify requesting an access token with a null refresh token is rejected")
    public void getRefreshToken_WithNullToken_ReturnsBadRequest() {
        Response response = authService.getRefreshToken(null);

        Assert.assertTrue(
                response.getStatusCode() == Status.BAD_REQUEST || response.getStatusCode() == Status.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }

    @Test(description = "Verify requesting an access token with an expired/revoked refresh token is rejected")
    public void getRefreshToken_WithExpiredToken_ReturnsUnauthorized() {
        String expiredRefreshToken = Config.getExpiredRefreshToken();
        Response response = authService.getRefreshToken(expiredRefreshToken);
        Assert.assertFalse(response.jsonPath().getBoolean("success"), Message.FAILS_TO_REJECT_EXPIRED_REFRESH_TOKEN);
        Assert.assertEquals(response.getStatusCode(), Status.UNAUTHORIZED);
    }
}