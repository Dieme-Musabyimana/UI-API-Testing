package api.services;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.POJOs.responsePOJO.registerResPOJO.RegisterResPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import io.restassured.response.Response;

import java.util.Map;

public class AuthService extends BaseService {

    public Response registerUser(RegisterReqPOJO reqBody) {
        return sendPost(Routes.REGISTER, reqBody);
    }

    public Response login(){
        LoginPOJO loginData = RequestPayloads.createLoginBody();
        return sendPost(Routes.LOGIN, loginData);
    }
    public Response verifyEmail(String emailToken) {
        String path = Routes.VERIFY_EMAIL + emailToken;
        return sendGet(path);

    }
    public Response registerAndVerifyEmail(RegisterReqPOJO signUpPayload) {
        Response regResponse = registerUser(signUpPayload);

        RegisterResPOJO regResBody = regResponse.getBody().as(RegisterResPOJO.class);
        String emailToken = regResBody.getData().getToken();
        return verifyEmail(emailToken);
    }

    public Response requestForgotPasswordEmail(){
        return sendPost(Routes.FORGOT_PASSWORD, Map.of("email", Config.getTestEmail()));
    }
    public Response getCurrentUser(){
        return sendGet(Routes.GET_ME);
    }

    public Response loginAndGetCurrentUser(){
        String token = login().jsonPath().get("data.token");
        return sendGetWithAuth(Routes.GET_ME, token);
    }

    public Response loginAndGetRefreshToken(){
        String refreshToken = login().jsonPath().getString("data.refreshToken");
        return  sendPost(Routes.REFRESH_TOKEN, Map.of("refreshToken", refreshToken));
    }


    public Response loginAndResetPassword(){
        String token = login().jsonPath().getString("data.token");
         String path = Routes.RESET_PASSWORD + token;
        return sendPost(path, Map.of("password", Config.getLoginpsswd() ));
    }
}