package apitests.AuthAPI;

import api.base.BaseAPI;
import api.services.AuthService;
import api.utils.TokenManager;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseAPI {

    @Test
    public void resetPasswordTestWithLogin(){
        AuthService authService = new AuthService();
        String token = new TokenManager().getAllTokens().getFirst();
        authService.resetPassword(token);

    }
}
