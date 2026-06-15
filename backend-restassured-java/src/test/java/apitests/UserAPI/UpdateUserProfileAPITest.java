package apitests.UserAPI;
import api.base.BaseAPI;
import api.constants.Status;
import api.services.UserService;
import api.utils.Expected;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.services.UserService.firsName;
import static api.services.UserService.lastName;
import static api.utils.LoginAs.ADMIN;
import static api.utils.LoginAs.NONE;


public class UpdateUserProfileAPITest extends BaseAPI {

    @Test
    public void updateProfileWithLogin(){
        UserService userService = new UserService();
        Response response = userService.updateProfile(firsName, lastName, ADMIN);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
    }
    @Test
    public void updateProfileWithoutLogin(){
        UserService userService = new UserService();
        Response response = userService.updateProfile(firsName, lastName, NONE);

        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.PROFILE_UPDATED);
        Assert.assertEquals(response.jsonPath().getString("data.firstName"), firsName);
        Assert.assertEquals(response.jsonPath().getString("data.lastName"), lastName);

    }

    @Test
    public void updateProfileWithEmptyFields(){
        Response response = new UserService().updateProfile(" ", " ", ADMIN);
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST);
    }

}
