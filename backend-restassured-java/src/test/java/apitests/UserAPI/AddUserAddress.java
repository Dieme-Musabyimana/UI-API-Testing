package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.services.UserService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddUserAddress extends BaseAPI {
    UserService userService;

    @BeforeMethod
    public void setUp(){
        this.userService = new UserService();
    }

    @Test

    public void addAddressTest(){
        Response response = userService.addAddress();
        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED);
        Assert.assertEquals(response.jsonPath().getString("data.firstName"), UserService.firsName);
        Assert.assertEquals(response.jsonPath().getString("data.lastName"), UserService.lastName);
        Assert.assertTrue(response.jsonPath().get("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.ADDRESS_ADDED);
    }
    @Test
    public void AddAddressWithSomEmptyFied(){
        userService.addAddressWithEmptyFields();

    }
}
