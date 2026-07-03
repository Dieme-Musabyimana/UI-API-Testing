package apitests.AuthAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import api.utils.Faker;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyemailTest extends BaseAPI {
    private AuthService authService;

    @BeforeMethod
    public void setup() {
        authService = new AuthService();
    }

    @Test(description = "Verify email with a valid token")
    public void shouldVerifyEmailWithValidToken() {
        String lastName = Faker.getLastName();
        String email = Faker.getEmail();
        String password = Faker.getPassword();

        String token = authService.registerUser(lastName, email, password)
                .jsonPath()
                .getString("token");

        Response response = authService.verifyEmail(token);

        Assert.assertEquals(response.getStatusCode(), Status.OK, Message.FAILS_TO_VERIFY_EMAIL_WITH_VALID_TOKEN);
    }

    @Test(description = "Verify email with an invalid token")
    public void shouldFailToVerifyEmailWithInvalidToken() {
        Response response = authService.verifyEmail("invalid-token");

        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }

    @Test(description = "Verify email with an empty token")
    public void shouldFailToVerifyEmailWithEmptyToken() {
        Response response = authService.verifyEmail("");

        Assert.assertEquals(response.getStatusCode(), Status.NOT_FOUND);
    }

    @Test(description = "Verify email with a malformed token")
    public void shouldFailToVerifyEmailWithMalformedToken() {
        Response response = authService.verifyEmail("@@@###$$$");

        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }

    @Test(description = "Verify email with a whitespace token")
    public void shouldFailToVerifyEmailWithWhitespaceToken() {
        Response response = authService.verifyEmail(" ");

        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }
}
