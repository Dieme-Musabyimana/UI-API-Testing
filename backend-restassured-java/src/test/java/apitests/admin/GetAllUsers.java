package apitests.admin;

import api.constants.Status;
import api.services.AdminService;
import api.utils.Expected;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.utils.LoginAs.ADMIN;
import static api.utils.LoginAs.CUSTOMER;

public class GetAllUsers {

    @Test
    public void getAllUsersAsAnAdmin(){
        Response response = new AdminService().getAllUsers(ADMIN);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertNotNull((response.jsonPath().get("data")));
    }

    @Test
    public void getAllUserNotAsAnAdmin(){
        Response response = new AdminService().getAllUsers(CUSTOMER);
        Assert.assertEquals(response.statusCode(), Status.FORBIDDEN);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.PERMISSION_ISSUE);
        Assert.assertNull(response.jsonPath().get("data"));
    }

    @Test
    public void getAllProductWithoutLogin(){
        Response response = new AdminService().getAllUsers(LoginAs.NONE);
        Assert.assertEquals(response.statusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
        Assert.assertNull(response.jsonPath().get("data"));
    }
}
