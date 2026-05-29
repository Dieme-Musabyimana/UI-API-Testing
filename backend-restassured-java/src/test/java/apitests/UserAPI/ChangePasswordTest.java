package apitests.UserAPI;

import api.services.UserService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest {
    UserService userService;

    @BeforeMethod
    public void setUp(){
        this.userService = new UserService();
    }

    @Test
    public void changePasswordTest(){
        userService.changePassword();
    }

}
