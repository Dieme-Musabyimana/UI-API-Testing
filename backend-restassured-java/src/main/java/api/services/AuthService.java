package api.services;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import api.utils.Faker;
import api.utils.LoginAs;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static api.utils.LoginAs.ADMIN;
import static api.utils.LoginAs.NONE;

public class AuthService extends BaseService {


    public Response registerUser(String lastName, String email, String password) {
        RegisterReqPOJO reqBody = RequestPayloads.createReqBody(lastName, email, password);
        return sendPost(Routes.REGISTER, reqBody, null, NONE);
    }

    public Response login(String email,String password, LoginAs loginAs){
        LoginPOJO loginData = RequestPayloads.getCredentials(loginAs);
        if(email != null)loginData.setEmail(email);
       if(password != null)loginData.setPassword(password);
       return sendPost(Routes.LOGIN, loginData, null,loginAs);
    }


    public Response registerAndVerifyEmail() {
        String lastName = Faker.getLastName();
        String email = Faker.getEmail();
        String password = Faker.getPassword();
        String token = registerUser(lastName, email, password).jsonPath().getString("data.token");
        Map<String, Object> param = Map.of("token", token);
        return sendGet(Routes.VERIFY_EMAIL, param, param, ADMIN);

    }

    public Response requestForgotPasswordEmail(String email){
        Map<String, Object> query = new HashMap<>();
        if(email != null)query.put("email", email);
        return sendPost(Routes.FORGOT_PASSWORD, query, null,null);
    }

    public Response getCurrentUser(LoginAs loginAs){
        return sendGet(Routes.GET_ME, null, null, loginAs);
    }

    public Response getRefreshToken(String refreshToken){
        Map<String, Object> body = new HashMap<>();
        body.put("refreshToken",refreshToken);
        return  sendPost(Routes.REFRESH_TOKEN, body, null, ADMIN);
    }

    public Response resetPassword(String token){
        Map<String, Object> path = Map.of("token", token);
        return sendPost(Routes.RESET_PASSWORD, Map.of("password", Config.getCustomerLoginPassword()), path, NONE);
    }
}