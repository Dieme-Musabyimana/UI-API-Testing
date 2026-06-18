package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.UserService;
import api.utils.Expected;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AddUserAddress extends BaseAPI {
    UserService userService;

    @BeforeMethod
    public void setUp() {
        this.userService = new UserService();
    }
    @Test
    public void addAddressWithValidCredentials() {
        Response response = userService.addAddress(UserService.firsName, UserService.lastName, LoginAs.ADMIN);

        Assert.assertEquals(response.getStatusCode(), Status.CREATED);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.ADDRESS_ADDED);
        Assert.assertEquals(response.jsonPath().getString("data.firstName"), UserService.firsName);
        Assert.assertEquals(response.jsonPath().getString("data.lastName"), UserService.lastName);
    }

    @Test
    public void addAddressWithSomeEmptyFields() {
        Response response = userService.addAddress(" ", "", LoginAs.ADMIN);

        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertNotNull(response.jsonPath().getString("message"));
    }

    @Test
    public void addAddressWithNullFields() {
        Response response = userService.addAddress(null, null, LoginAs.ADMIN);

        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }

    @Test
    public void addAddressWithExcessiveFieldLengths() {
        String veryLongFirstName = "A".repeat(256);
        String veryLongLastName = "B".repeat(256);
        Response response = userService.addAddress(veryLongFirstName, veryLongLastName, LoginAs.ADMIN);
        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }

    @Test
    public void addAddressAsRegularUser() {
        Response response = userService.addAddress(UserService.firsName, UserService.lastName, LoginAs.CUSTOMER);

        Assert.assertEquals(response.getStatusCode(), Status.CREATED);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
    }

    @Test
    public void addAddressWithoutLogin() {
        Response response = userService.addAddress(UserService.firsName, UserService.lastName, LoginAs.NONE);
        Assert.assertEquals(response.getStatusCode(), Status.UNAUTHORIZED);
    }
}