package api.POJOs.responsePOJO.registerResPOJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class LoginResPOJO extends RegisterResPOJO{
    @Getter
    @Setter
    @JsonProperty("phone")
    private String phone;


    }

