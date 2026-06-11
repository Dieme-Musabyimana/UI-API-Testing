package api.services;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import io.restassured.response.Response;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;

import static api.utils.TokenManager.getRefreshToken;
import static api.utils.TokenManager.getToken;

public class AuthService extends BaseService {


    public Response registerUser() {
        RegisterReqPOJO reqBody = RequestPayloads.createReqBody();
        return sendPost(Routes.REGISTER, reqBody);
    }

    public Response login(){
        LoginPOJO loginData = RequestPayloads.createLoginBody();
        return sendPost(Routes.LOGIN, loginData);
    }


    public Response registerAndVerifyEmail() {
        String path = Routes.VERIFY_EMAIL + getRefreshToken();
        return sendGet(path);

    }

    public Response requestForgotPasswordEmail(){
        return sendPost(Routes.FORGOT_PASSWORD, Map.of("email", Config.getTestEmail()));
    }

    public Response getCurrentUser(){
        return sendGet(Routes.GET_ME);
    }
    public Response loginAndGetCurrentUser(){
        return sendGetWithAuth(Routes.GET_ME, null);
    }

    public Response loginAndGetRefreshToken(){
        return  sendPost(Routes.REFRESH_TOKEN, Map.of("refreshToken", getRefreshToken()));
    }

    public Response loginAndResetPassword(){
         String path = Routes.RESET_PASSWORD + getToken();
        return sendPost(path, Map.of("password", Config.getLoginpsswd() ));
    }
}