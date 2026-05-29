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

    public UserService(){
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
        loginBody.put("password", ConfigReader.getLoginPassd2());
      Response response =  sendPost(Routes.LOGIN, loginBody);
      return response.jsonPath().getString(ConfigReader.getTokenPath());

    }

    public Response changePassword(String token){
        Map<String, String> updatedLoginBody = new HashMap<>();
        updatedLoginBody.put("currentPassword", ConfigReader.getLoginPassd2());
        updatedLoginBody.put("newPassword", FakerUtils.getPassword());
        return sendPutWithAuth(Routes.CHANGE_PASSWORD, updatedLoginBody, token);
    }

    public Response getUserAddress(String token){
        return sendGetWithAuth(Routes.ADDRESS, token);
    }

    public static Map<String, Object> getAddressPayload() {
        Map<String, Object> addressBody = new HashMap<>();
        addressBody.put("label", "Home");
        addressBody.put("firstName", FakerUtils.getFirstName());
        addressBody.put("lastName", FakerUtils.getLastName());
        addressBody.put("phone", FakerUtils.getPhone());
        addressBody.put("street", "KG 541 St");
        addressBody.put("city", "Kigali");
        addressBody.put("state", "Kigali City");
        addressBody.put("country", "Rwanda");
        addressBody.put("postalCode", "00000");
        addressBody.put("isDefault", true);

        return addressBody;
    }

    public Response addAddress(){
        String token = authService.login().jsonPath().getString(ConfigReader.getTokenPath());
        return sendPostWithAuth(Routes.ADDRESS,getAddressPayload(),token);
    }


}
