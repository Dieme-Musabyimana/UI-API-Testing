package apitests.UserAPI;

import api.services.UserService;
import org.testng.annotations.Test;

public class FileUploadTest {

    @Test
    public void uploadTest(){
        UserService userService = new UserService();
        userService.loginAndUpdateProfile();
    }
}
