package api.POJOs.responsePOJO.registerResPOJO;

import api.POJOs.base.BaseRegPojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserRegistrationData extends BaseRegPojo {

    private String id;
    private String role;

    @JsonProperty("isVerified")
    private boolean isVerified;
    private String avatar;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("createdAt")
    private String createdAt;


}