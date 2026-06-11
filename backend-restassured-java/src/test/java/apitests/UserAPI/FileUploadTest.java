package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.UserService;
import api.utils.Config;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static api.utils.TokenManager.getToken;

public class FileUploadTest extends BaseAPI {
    UserService userService;
    String token;
    @BeforeMethod
    public void setUp(){
       this.userService = new UserService();
    }

    @Test
    public void uploadTest(){
        File testImage = new File(Config.getFilePath());

        Response response = userService.uploadAvatar(testImage, getToken());
        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AVATAR_UPLOADED);
      }
    @Test
    public void uploadWithoutLoginTest(){
        File testImage = new File(Config.getFilePath());
        Response response = userService.uploadAvatar(testImage, " ");
        Assert.assertEquals(response.statusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);


    }

    @Test
    public void uploadWithEmptyFileTest() throws IOException{
        File dir = new File(Config.getTempFilePath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File tempEmptyFile = File.createTempFile(Config.getTempFileName() , Config.getFormat(),dir);
        tempEmptyFile.deleteOnExit();
        Response response = new UserService().uploadAvatar(tempEmptyFile, token);
        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));

    }
}
