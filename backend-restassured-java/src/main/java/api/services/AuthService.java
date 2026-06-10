package api.services;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import api.utils.TokenManager;
import io.restassured.response.Response;

import java.util.Map;

public class AuthService extends BaseService {
    TokenManager tokenManager;
    String token;
    String refreshToken;

    public AuthService(){
        this.tokenManager = new TokenManager();
        this.token = tokenManager.getToken();
        this.refreshToken = tokenManager.getRefreshToken();
    }

    public Response registerUser() {
        RegisterReqPOJO reqBody = RequestPayloads.createReqBody();
        return sendPost(Routes.REGISTER, reqBody);
    }

    public Response login(){
        LoginPOJO loginData = RequestPayloads.createLoginBody();
        return sendPost(Routes.LOGIN, loginData);
    }


    public Response registerAndVerifyEmail() {
        String path = Routes.VERIFY_EMAIL + refreshToken;
        return sendGet(path);

    }

    public Response requestForgotPasswordEmail(){
        return sendPost(Routes.FORGOT_PASSWORD, Map.of("email", Config.getTestEmail()));
    }

    public Response getCurrentUser(){
        return sendGet(Routes.GET_ME);
    }
    public Response loginAndGetCurrentUser(){
        return sendGetWithAuth(Routes.GET_ME, token);
    }

    public Response loginAndGetRefreshToken(){
        return  sendPost(Routes.REFRESH_TOKEN, Map.of("refreshToken", refreshToken));
    }

    public Response loginAndResetPassword(){
         String path = Routes.RESET_PASSWORD + token;
        return sendPost(path, Map.of("password", Config.getLoginpsswd() ));
    }
}