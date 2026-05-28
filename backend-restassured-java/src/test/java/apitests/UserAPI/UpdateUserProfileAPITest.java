package apitests.UserAPI;

import api.base.BaseAPI;
import api.services.UserService;
import api.utils.Expectations;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserProfileAPITest extends BaseAPI {

    @Test
    public void updateProfileWithoutToken(){
        UserService userService = new UserService();
       Response response =  userService.fillUpdateInf();

    }

    @Test
    public void updateProfileWithToken(){
        UserService userService = new UserService();
        Response response = userService.loginAndUpdateProfile();




    }

}
