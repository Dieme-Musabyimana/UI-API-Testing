package api.services;

import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import api.utils.Faker;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserService extends BaseService {
    public static final String firsName = Faker.getFirstName();
    public static final String lastName = Faker.getFirstName();

    AuthService authService;


    public UserService(){
       this.authService = new AuthService();

    }

    public Response fillUpdateInf(){

        RegisterReqPOJO registerReqPOJO = RequestPayloads.createReqBody();
        registerReqPOJO.setFirstName(firsName);
        registerReqPOJO.setLastName(lastName);
        registerReqPOJO.setPhone(Faker.getPhone());
        return sendPutWithAuth(Routes.UPDATE_PROFILE, registerReqPOJO);
    }

    public Response loginAndUpdateProfile(){
        RegisterReqPOJO registerReqPOJO = RequestPayloads.createReqBody();
        registerReqPOJO.setFirstName(firsName);
        registerReqPOJO.setLastName(lastName);
        registerReqPOJO.setPhone(Faker.getPhone());
        return sendPutWithAuth(Routes.UPDATE_PROFILE, registerReqPOJO);


    }

    public Response uploadAvatar(File image){
        return sendPostMultipartWithAuth(Routes.UPLOAD_AVATAR, image, "avatar");
    }

    public String login2(){
        Map<String, String> loginBody = new HashMap<>();
        loginBody.put("email", Config.getLoginEmail2());
        loginBody.put("password", Config.getLoginPassd2());
      Response response =  sendPost(Routes.LOGIN, loginBody);
      return response.jsonPath().getString(Config.getTokenPath());

    }

    public Response changePassword(){
        RequestPayloads payloads = new RequestPayloads();
        return sendPutWithAuth(Routes.CHANGE_PASSWORD, payloads.changePasswordBody());
    }

    public Response changePasswordWithoutLogin(){
        RequestPayloads payloads = new RequestPayloads();
        return sendPut(Routes.CHANGE_PASSWORD, payloads.changePasswordBody());
    }

    public Response getUserAddress(){
        return sendGetWithAuth(Routes.ADDRESS, null);
    }

    public static Map<String, Object> getAddressPayload() {

        Map<String, Object> addressBody = new HashMap<>();
        addressBody.put("label", Config.getLabel());
        addressBody.put("firstName", firsName);
        addressBody.put("lastName", lastName);
        addressBody.put("phone", Faker.getPhone());
        addressBody.put("street", Config.getStreet());
        addressBody.put("city", Config.getCity());
        addressBody.put("state", Config.getState());
        addressBody.put("country", Config.getCountry());
        addressBody.put("postalCode",Config.getPostalCode());
        addressBody.put("isDefault", true);

        return addressBody;
    }

    public Response addAddress(){
        return sendPostWithAuth(Routes.ADDRESS,getAddressPayload());
    }
public Response addAddressWithEmptyFields(){
    Map<String, Object> unCompleteBody = getAddressPayload();
    unCompleteBody.put("firstName", "");
    unCompleteBody.put("lastName", "");
    return sendPostWithAuth(Routes.ADDRESS, unCompleteBody);
}

}
