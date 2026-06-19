package api.POJOs.requestPOJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoginPOJO {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public LoginPOJO() {}

    public LoginPOJO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}