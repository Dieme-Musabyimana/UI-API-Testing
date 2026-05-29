package api.services;

import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.FakerUtils;
import io.restassured.response.Response;

import java.io.File;

public class UserService extends BaseService {

    public Response fillUpdateInf(){
        RegisterReqPOJO registerReqPOJO = RequestPayloads.createReqBody();
        registerReqPOJO.setFirstName(FakerUtils.getFirstName());
        registerReqPOJO.setLastName(FakerUtils.getLastName());
        registerReqPOJO.setPhone(FakerUtils.getPhone());
        return sendPutWithAuth(Routes.UPDATE_PROFILE, registerReqPOJO, "");
    }

    public Response loginAndUpdateProfile(){
        AuthService authService = new AuthService();
        String token = authService.login().jsonPath().getString("data.token");
        RegisterReqPOJO registerReqPOJO = RequestPayloads.createReqBody();
        registerReqPOJO.setFirstName(FakerUtils.getFirstName());
        registerReqPOJO.setLastName(FakerUtils.getLastName());
        registerReqPOJO.setPhone(FakerUtils.getPhone());
        return sendPutWithAuth(Routes.UPDATE_PROFILE, registerReqPOJO, token);


    }

    public Response uploadAvatar(File image){
        AuthService authService = new AuthService();
        String token = authService.login().jsonPath().getString("data.token");
        return sendPostMultipartWithAuth(Routes.UPLOAD_AVATAR, image, "avatar", token);
    }
}
