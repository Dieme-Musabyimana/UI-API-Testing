package api.services;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import io.restassured.response.Response;

import java.util.Map;

public class AuthService extends BaseService {


    public Response registerUser() {
        RegisterReqPOJO reqBody = RequestPayloads.createReqBody();
        return sendPost(Routes.REGISTER, reqBody, null, false);
    }

    public Response login(){
        LoginPOJO loginData = RequestPayloads.createLoginBody();
        return sendPost(Routes.LOGIN, loginData, null,false);
    }


    public Response registerAndVerifyEmail() {
        String token = registerUser().jsonPath().getString("data.token");
        Map<String, Object> param = Map.of("token", token);
        return sendGet(Routes.VERIFY_EMAIL, param, param, true);

    }

    public Response requestForgotPasswordEmail(){
        return sendPost(Routes.FORGOT_PASSWORD, Map.of("email", Config.getTestEmail()), null,true);
    }

    public Response getCurrentUser(boolean userAuth){
        return sendGet(Routes.GET_ME, null, null, userAuth);
    }

    public Response getRefreshToken(){
        return  sendPost(Routes.REFRESH_TOKEN, Map.of("refreshToken", getRefreshToken()), null,false);
    }

    public Response resetPassword(String token){
        Map<String, Object> path = Map.of("token", token);
        return sendPost(Routes.RESET_PASSWORD, Map.of("password", Config.getLoginpsswd()), path, false);
    }
}