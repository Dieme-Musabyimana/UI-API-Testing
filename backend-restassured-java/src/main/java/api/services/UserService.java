package api.services;

import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Faker;
import api.utils.LoginAs;
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

    public Response updateProfile(String firsName, String lastName, LoginAs loginAs){
        RegisterReqPOJO body = new RequestPayloads().updateProfilePayload(firsName, lastName);
        return sendPut(Routes.UPDATE_PROFILE, body, null, loginAs);
    }

    public Response uploadAvatar(File image){
        return sendPostMultipartWithAuth(Routes.UPLOAD_AVATAR, image, "avatar", null, LoginAs.NONE);
    }

    public Response changePassword(LoginAs loginAs){
        RequestPayloads payloads = new RequestPayloads();
        return sendPut(Routes.CHANGE_PASSWORD, payloads.changePasswordBody(), null,loginAs);
    }

    public Response getUserAddress(LoginAs loginAs){
        return sendGet(Routes.ADDRESS, null, null, loginAs);
    }

    public Response addAddress(String firsName, String lastName, LoginAs signIn){
        Map<String, Object> body = new RequestPayloads().getAddressPayload(firsName, lastName);
        return sendPost(Routes.ADDRESS,body, null, signIn);
    }
}
