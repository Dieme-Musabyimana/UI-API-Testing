package apitests.UserAPI;

import api.constants.StatusCodes;
import api.services.UserService;
import api.utils.ConfigReader;
import api.utils.Expectations;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTest {

    @Test
    public void uploadTest(){
        UserService userService = new UserService();
        File testImage = new File(ConfigReader.getFilePath());
        Response response = userService.uploadAvatar(testImage);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expectations.AVATAR_UPLOADED);
    }
}
