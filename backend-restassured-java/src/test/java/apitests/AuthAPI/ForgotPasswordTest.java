package apitests.AuthAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import api.utils.Config;
import api.utils.Expected;
import api.utils.Faker;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseAPI {

    private final AuthService authService = new AuthService();


    @Test(description = "Verify forgot password email request succeeds with a registered customer email")
    public void forgotPassword_ValidCustomerEmail_ReturnsSuccess() {
        String email = Config.getCustomerLoginEmail();
        Response response = authService.requestForgotPasswordEmail(email);

        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.successMessage);
    }

    @Test(description = "Verify forgot password email request succeeds with a registered admin email")
    public void forgotPassword_ValidAdminEmail_ReturnsSuccess() {
        String email = Config.getAdminLoginEmail();
        Response response = authService.requestForgotPasswordEmail(email);
        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
    }

    @Test(description = "Verify a non-existent/unregistered email address returns an error (or a secure response)")
    public void forgotPassword_UnregisteredEmail_ReturnsNotFound() {
        String unregisteredEmail = Faker.getEmail();
        Response response = authService.requestForgotPasswordEmail(unregisteredEmail);
        Assert.assertFalse(response.jsonPath().getBoolean("success"),Message.FAILS_TO_REJECT_UNREGISTERED_EMAIL);
        Assert.assertEquals(response.getStatusCode(), Status.NOT_FOUND);
    }

    @Test(description = "Verify providing a malformed email string returns a Bad Request error")
    public void forgotPassword_MalformedEmailFormat_ReturnsBadRequest() {
        String malformedEmail = "fakeemail@";
        Response response = authService.requestForgotPasswordEmail(malformedEmail);
        Assert.assertFalse(response.jsonPath().getBoolean("success"), Message.FAILS_TO_REJECT_MALFORMED_EMAIL);
        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }

    @Test(description = "Verify providing an empty email field returns a validation error")
    public void forgotPassword_EmptyEmail_ReturnsBadRequest() {
        Response response = authService.requestForgotPasswordEmail("");
        Assert.assertFalse(response.jsonPath().getBoolean("success"), Message.FAILS_TO_REJECT_EMPTY_FIELD_EMAIL);
        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);

    }

    @Test(description = "Verify sending a null value for the email address returns a validation error")
    public void forgotPassword_NullEmail_ReturnsBadRequest() {
        Response response = authService.requestForgotPasswordEmail(null);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));

    }
}