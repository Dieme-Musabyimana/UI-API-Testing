package api.payloads;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.utils.Config;
import api.utils.FakerUtils;

import java.sql.Statement;
import java.util.Map;

public class RequestPayloads {

    public static RegisterReqPOJO createReqBody(){
        RegisterReqPOJO registerData = new RegisterReqPOJO();
        registerData.setFirstName(FakerUtils.getFirstName());
        registerData.setLastName(FakerUtils.getLastName());
        registerData.setEmail(FakerUtils.getEmail());
        registerData.setPassword(FakerUtils.getPassword());
        registerData.setPhone(FakerUtils.getPhone());

        return registerData;
    }

    public static LoginPOJO createLoginBody(){
        LoginPOJO loginData = new LoginPOJO();
        loginData.setEmail(Config.getLoginEmail());
        loginData.setPassword(Config.getLoginpsswd());

        return loginData;
    }


    public Map<String, Object> createProductBody(){
        createProductBody().put("name", "Sport");
        createProductBody().put("description", "For doing sports");
        createProductBody().put("parentId", "Spots_clothes");
        return createProductBody();
    }
}
