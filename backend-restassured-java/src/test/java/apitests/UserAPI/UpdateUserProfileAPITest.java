package apitests.UserAPI;

import api.base.BaseAPI;
import api.services.AuthService;
import api.services.UserService;
import api.utils.Expectations;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserProfileAPITest extends BaseAPI {

    @Test
    public void updateProfileWithoutToken() {
        UserService userService = new UserService();
        Response response = userService.fillUpdateInf();
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expectations.AUTHENTICATION_ERROR);
    }
    @Test
    public void updateProfileWithToken(){
        UserService userService = new UserService();
        Response response = userService.loginAndUpdateProfile();

        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expectations.PROFILE_UPDATED);
        Assert.assertEquals(response.jsonPath().getString("data.firstName"), UserService.firsName);
        Assert.assertEquals(response.jsonPath().getString("data.lastName"), UserService.lastName);

    }

}
