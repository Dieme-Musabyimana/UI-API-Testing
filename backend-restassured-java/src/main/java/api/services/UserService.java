package api.services;

import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.ConfigReader;
import api.utils.FakerUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserService extends BaseService {
    AuthService authService;

    @BeforeMethod
    public void setUp(){
       this.authService = new AuthService();
    }

    public Response fillUpdateInf(){
        RegisterReqPOJO registerReqPOJO = RequestPayloads.createReqBody();
        registerReqPOJO.setFirstName(FakerUtils.getFirstName());
        registerReqPOJO.setLastName(FakerUtils.getLastName());
        registerReqPOJO.setPhone(FakerUtils.getPhone());
        return sendPutWithAuth(Routes.UPDATE_PROFILE, registerReqPOJO, "");
    }

    public Response loginAndUpdateProfile(){
        String token = authService.login().jsonPath().getString("data.token");
        RegisterReqPOJO registerReqPOJO = RequestPayloads.createReqBody();
        registerReqPOJO.setFirstName(FakerUtils.getFirstName());
        registerReqPOJO.setLastName(FakerUtils.getLastName());
        registerReqPOJO.setPhone(FakerUtils.getPhone());
        return sendPutWithAuth(Routes.UPDATE_PROFILE, registerReqPOJO, token);


    }

    public Response uploadAvatar(File image, String token){
        return sendPostMultipartWithAuth(Routes.UPLOAD_AVATAR, image, "avatar", token);
    }

    public String login2(){
        Map<String, String> loginBody = new HashMap<>();
        loginBody.put("email", ConfigReader.getLoginEmail2());
        loginBody.put("password", ConfigReader.getLoginEmail2());
      Response response =  sendPost(Routes.LOGIN, loginBody);
      return response.jsonPath().getString(ConfigReader.getTokenPath());

    }

    public Response changePassword(){
        String token = login2();
        Map<String, String> updatedLoginBody = new HashMap<>();
        updatedLoginBody.put("currentPassword", ConfigReader.getLoginpsswd());
        updatedLoginBody.put("newPassword", FakerUtils.getPassword());

        return sendPutWithAuth(Routes.CHANGE_PASSWORD, updatedLoginBody, token);


    }


}
