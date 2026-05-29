package apitests.AuthAPI;

import api.base.BaseAPI;
import api.services.AuthService;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseAPI {

    @Test
    public void resetPasswordTest(){
        AuthService authService = new AuthService();
        authService.loginAndResetPassword();

    }
}
