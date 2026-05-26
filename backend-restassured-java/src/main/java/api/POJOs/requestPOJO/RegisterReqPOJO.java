package api.POJOs.requestPOJO;

import api.POJOs.base.BaseRegPojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class RegisterReqPOJO extends BaseRegPojo {

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("password")
    private String password;


    public RegisterReqPOJO() {
        super();
    }

    }
