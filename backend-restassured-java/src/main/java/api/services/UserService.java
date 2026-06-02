package api.services;

import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import api.utils.FakerUtils;
import groovy.transform.Final;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserService extends BaseService {
    AuthService authService;
    public static final String firstName = FakerUtils.getFirstName();
    public static final String secondName = FakerUtils.getLastName();

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
        loginBody.put("email", Config.getLoginEmail2());
        loginBody.put("password", Config.getLoginPassd2());
      Response response =  sendPost(Routes.LOGIN, loginBody);
      return response.jsonPath().getString(Config.getTokenPath());

    }

    public Response changePassword(String token){
        Map<String, String> updatedLoginBody = new HashMap<>();
        updatedLoginBody.put("currentPassword", Config.getLoginPassd2());
        updatedLoginBody.put("newPassword", FakerUtils.getPassword());
        return sendPutWithAuth(Routes.CHANGE_PASSWORD, updatedLoginBody, token);
    }

    public Response getUserAddress(String token){
        return sendGetWithAuth(Routes.ADDRESS, token);
    }

    public static Map<String, Object> getAddressPayload() {

        Map<String, Object> addressBody = new HashMap<>();
        addressBody.put("label", Config.getLabel());
        addressBody.put("firstName", firstName);
        addressBody.put("lastName", secondName);
        addressBody.put("phone", FakerUtils.getPhone());
        addressBody.put("street", Config.getStreet());
        addressBody.put("city", Config.getCity());
        addressBody.put("state", Config.getState());
        addressBody.put("country", Config.getCountry());
        addressBody.put("postalCode",Config.getPostalCode());
        addressBody.put("isDefault", true);

        return addressBody;
    }

    public Response addAddress(){
        String token = authService.login().jsonPath().getString(Config.getTokenPath());
        return sendPostWithAuth(Routes.ADDRESS,getAddressPayload(),token);
    }
public Response addAddressWithEmptyFields(){
    String token = authService.login().jsonPath().getString(Config.getTokenPath());
    Map<String, Object> unCompleteBody = getAddressPayload();
    unCompleteBody.put("firstName", "");
    unCompleteBody.put("lastName", "");
    return sendPostWithAuth(Routes.ADDRESS, unCompleteBody, token);
}

}
