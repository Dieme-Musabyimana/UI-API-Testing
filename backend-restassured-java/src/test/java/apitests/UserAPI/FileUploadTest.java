package apitests.UserAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.UserService;
import api.utils.Config;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FileUploadTest extends BaseAPI {

    @Test
    public void uploadWithValidCredentials(){
        Response response =new UserService().uploadAvatar();
        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AVATAR_UPLOADED);
      }
    @Test
    public void uploadWithoutLoginTest(){
        Response response = new UserService().uploadAvatar();
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
        Response response = new UserService().uploadAvatar();
        Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));

    }
}
