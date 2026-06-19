package apitests.AuthAPI;

import api.base.BaseAPI;
import api.services.AuthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseAPI {

    @Test
    public void resetPasswordTestWithLogin(){
        AuthService authService = new AuthService();
        authService.resetPassword(authService.tokenList().getFirst());

    }
}
