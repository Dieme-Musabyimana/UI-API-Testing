package api.payloads;

import api.POJOs.requestPOJO.LoginPOJO;
import api.POJOs.requestPOJO.RegisterReqPOJO;
import api.utils.ConfigReader;
import api.utils.FakerUtils;

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
        loginData.setEmail(ConfigReader.getLoginEmail());
        loginData.setPassword(ConfigReader.getLoginpsswd());

        return loginData;
    }
}
