package apitests.AuthAPI;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.AuthService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RefreshTokenAPITest extends BaseAPI {

    @Test
    public void getRefreshToken(){
        AuthService authService = new AuthService();
       Response response = authService.getRefreshToken();
       Assert.assertEquals(response.getStatusCode(), Status.OK);
    }
}
