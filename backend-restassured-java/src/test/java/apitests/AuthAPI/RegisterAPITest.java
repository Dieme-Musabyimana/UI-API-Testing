package apitests.AuthAPI;

import api.POJOs.responsePOJO.registerResPOJO.RegisterResPOJO;
import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import api.utils.Expected;
import api.utils.Faker;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterAPITest extends BaseAPI {

    private AuthService authService;

    @BeforeMethod
    public void setUp() {
        this.authService = new AuthService();
    }
    @Test(description = "Verify registering a user with valid details succeeds")
    public void register_ValidDetails_ReturnsCreated() {
        String lastName = Faker.getLastName();
        String email = Faker.getEmail();
        String password = Faker.getPassword();

        Response response = authService.registerUser(lastName, email, password);
        RegisterResPOJO resBody = response.getBody().as(RegisterResPOJO.class);

        Assert.assertEquals(response.getStatusCode(), Status.CREATED);
        Assert.assertTrue(resBody.isSuccess());
        Assert.assertTrue(resBody.getMessage().contains(Expected.REGISTRATION_SUCCESS));

        Assert.assertNotNull(resBody.getData().getToken());
        Assert.assertNotNull(resBody.getData().getRefreshToken());
        Assert.assertEquals(resBody.getData().getUser().getLastName(), lastName);
        Assert.assertEquals(resBody.getData().getUser().getEmail(), email);
        Assert.assertEquals(resBody.getData().getUser().getRole(), "CUSTOMER");
        Assert.assertNotNull(resBody.getData().getUser().getId());
    }

    @Test(description = "Verify registering with an already existing/registered email is rejected")
    public void register_DuplicateEmail_ReturnsConflict() {
        String lastName = Faker.getLastName();
        String duplicateEmail = api.utils.Config.getCustomerLoginEmail();
        String password = Faker.getPassword();

        Response response = authService.registerUser(lastName, duplicateEmail, password);
        Assert.assertEquals(response.getStatusCode(), Status.CONFLICT);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.EMAIL_EXISTS);
    }

    @Test(description = "Verify registering with a malformed email format is rejected")
    public void register_InvalidEmailFormat_ReturnsBadRequest() {
        String lastName = Faker.getLastName();
        String invalidEmail = "malformed-email";
        String password = Faker.getPassword();

        Response response = authService.registerUser(lastName, invalidEmail, password);
        Assert.assertFalse(response.jsonPath().getBoolean("success"), Message.FAILS_TO_REJECT_MALFORMED_EMAIL_REGISTRATION);
        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }

    @Test(description = "Verify registering with an empty password string is rejected")
    public void register_EmptyPassword_ReturnsBadRequest() {
        String lastName = Faker.getLastName();
        String email = Faker.getEmail();

        Response response = authService.registerUser(lastName, email, "");
        Assert.assertFalse(response.jsonPath().getBoolean("success"), Message.FAILS_TO_REJECT_REGISTRATION_WITH_EMPTY_FIELDS);
        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }

    @Test(description = "Verify registering with an empty last name is rejected")
    public void register_EmptyLastName_ReturnsBadRequest() {
        String email = Faker.getEmail();
        String password = Faker.getPassword();

        Response response = authService.registerUser("", email, password);
        Assert.assertFalse(response.jsonPath().getBoolean("success"), Message.FAILS_TO_REJECT_REGISTRATION_WITH_EMPTY_FIELDS);
        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
    }

    @Test(description = "Verify registering with null values is rejected")
    public void register_NullValues_ReturnsBadRequest() {
        Response response = authService.registerUser(null, null, null);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }
}