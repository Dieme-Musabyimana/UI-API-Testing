package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.services.UserService;
import api.utils.Config;
import api.utils.Expected;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FileUploadTest extends BaseAPI {
    TokenManager tokenManager;
    UserService userService;
    String token;
    @BeforeMethod
    public void setUp(){
       this.tokenManager = new TokenManager();
       this.userService = new UserService();
       this.token = tokenManager.getToken();
    }

    @Test
    public void uploadTest(){
        File testImage = new File(Config.getFilePath());

        Response response = userService.uploadAvatar(testImage, token);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AVATAR_UPLOADED);
      }
    @Test
    public void uploadWithoutLoginTest(){
        File testImage = new File(Config.getFilePath());
        Response response = userService.uploadAvatar(testImage, " ");
        Assert.assertEquals(response.statusCode(), StatusCodes.UNAUTHORIZED);
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
        Assert.assertEquals(response.getStatusCode(), StatusCodes.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));

    }
}
