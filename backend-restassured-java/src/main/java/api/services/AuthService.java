package api.services;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import io.restassured.response.Response;

import java.util.Map;

import static api.utils.TokenManager.getRefreshToken;
import static api.utils.TokenManager.getToken;

public class AuthService extends BaseService {


    public Response registerUser() {
        RegisterReqPOJO reqBody = RequestPayloads.createReqBody();
        return sendPost(Routes.REGISTER, reqBody, false);
    }

    public Response login(){
        LoginPOJO loginData = RequestPayloads.createLoginBody();
        return sendPost(Routes.LOGIN, loginData, false);
    }


    public Response registerAndVerifyEmail() {
        String token = registerUser().jsonPath().getString("data.token");
        Map<String, Object> param = Map.of("token", token);
        return sendGet(Routes.VERIFY_EMAIL, param, null, true);

    }

    public Response requestForgotPasswordEmail(){
        return sendPost(Routes.FORGOT_PASSWORD, Map.of("email", Config.getTestEmail()), true);
    }

    public Response getCurrentUser(boolean userAuth){
        return sendGet(Routes.GET_ME, null, null, userAuth);
    }

    public Response loginAndGetRefreshToken(){
        return  sendPost(Routes.REFRESH_TOKEN, Map.of("refreshToken", getRefreshToken()), true);
    }

    public Response loginAndResetPassword(){
         String path = Routes.RESET_PASSWORD + getToken();
        return sendPost(path, Map.of("password", Config.getLoginpsswd()), true);
    }
}