package api.services;

import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Faker;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

public class UserService extends BaseService {
    public static final String firsName = Faker.getFirstName();
    public static final String lastName = Faker.getFirstName();

    AuthService authService;

    public UserService(){
       this.authService = new AuthService();
    }

    public Response updateProfile(String firsName, String lastName, boolean login){
        RegisterReqPOJO body = new RequestPayloads().updateProfilePayload(firsName, lastName);
        return sendPut(Routes.UPDATE_PROFILE, body, null, login);
    }

    public Response uploadAvatar(File image){
        return sendPostMultipartWithAuth(Routes.UPLOAD_AVATAR, image, "avatar", null);
    }

    public Response changePassword(boolean login){
        RequestPayloads payloads = new RequestPayloads();
        return sendPut(Routes.CHANGE_PASSWORD, payloads.changePasswordBody(), null,login);
    }

    public Response getUserAddress(boolean login){
        return sendGet(Routes.ADDRESS, null, null, login);
    }

    public Response addAddress(String firsName, String lastName, boolean login){
        Map<String, Object> body = new RequestPayloads().getAddressPayload(firsName, lastName);
        return sendPost(Routes.ADDRESS,body, null, login);
    }
}
