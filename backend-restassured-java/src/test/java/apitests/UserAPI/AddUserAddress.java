package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.services.UserService;
import api.utils.Expectations;
import api.utils.FakerUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddUserAddress extends BaseAPI {

    @Test

    public void addAddressTest(){
        UserService userService = new UserService();
        Response response = userService.addAddress();
        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED);
        Assert.assertEquals(response.jsonPath().getString("data.firstName"), UserService.firstName);
        Assert.assertEquals(response.jsonPath().getString("data.lastName"), UserService.secondName);
        Assert.assertTrue(response.jsonPath().get("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expectations.ADDRESS_ADDED);
    }
}
