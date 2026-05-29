package apitests.UserAPI;

import api.constants.StatusCodes;
import api.services.AuthService;
import api.services.UserService;
import api.utils.ConfigReader;
import api.utils.Expectations;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FileUploadTest {

    @Test
    public void uploadTest(){
        UserService userService = new UserService();
        File testImage = new File(ConfigReader.getFilePath());
        String token = new AuthService().login().jsonPath().getString("data.token");

        Response response = userService.uploadAvatar(testImage, token);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expectations.AVATAR_UPLOADED);
    }

    @Test
    public void uploadWithoutLoginTest(){
        UserService userService = new UserService();
        File testImage = new File(ConfigReader.getFilePath());
        Response response = userService.uploadAvatar(testImage, " ");
        Assert.assertEquals(response.statusCode(), StatusCodes.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expectations.NO_AUTHENTICATION);


    }

    @Test
    public void uploadWithNoFileTest() throws IOException{
        File tempEmptyFile = File.createTempFile("empty_avatar", ".png");
        tempEmptyFile.deleteOnExit();
        String token = new AuthService().login().jsonPath().getString("data.token");
        Response response = new UserService().uploadAvatar(tempEmptyFile, token);
        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertNull(response.jsonPath().getString("data.avatar"));

    }
}
