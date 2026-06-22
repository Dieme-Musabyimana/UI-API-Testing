package api.services;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import api.utils.LoginAs;
import api.utils.TokenManager;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static api.utils.LoginAs.ADMIN;

public class AuthService extends BaseService {


    public Response registerUser() {
        RegisterReqPOJO reqBody = RequestPayloads.createReqBody();
        return sendPost(Routes.REGISTER, reqBody, null, null);
    }

    public Response login(String email,String password, LoginAs loginAs){
        LoginPOJO loginData = RequestPayloads.getCredentials(loginAs);
        loginData.setEmail(email);
        loginData.setPassword(password);
        return sendPost(Routes.LOGIN, loginData, null,loginAs);
    }


    public Response registerAndVerifyEmail() {
        String token = registerUser().jsonPath().getString("data.token");
        Map<String, Object> param = Map.of("token", token);
        return sendGet(Routes.VERIFY_EMAIL, param, param, ADMIN);

    }

    public Response requestForgotPasswordEmail(){
        return sendPost(Routes.FORGOT_PASSWORD, Map.of("email", Config.getCustomerLoginEmail()), null,null);
    }
public List<String> tokenList(){
        return new TokenManager().getAllTokens();
}
    public Response getCurrentUser(LoginAs loginAs){
        return sendGet(Routes.GET_ME, null, null, loginAs);
    }

    public Response getRefreshToken(){
        return  sendPost(Routes.REFRESH_TOKEN, Map.of("refreshToken", tokenList().getLast()), null, ADMIN);
    }

    public Response resetPassword(String token){
        Map<String, Object> path = Map.of("token", token);
        return sendPost(Routes.RESET_PASSWORD, Map.of("password", Config.getCustomerLoginPassword()), path, null);
    }
}