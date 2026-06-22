package apitests.schemas;

import api.services.AuthService;
import api.utils.Config;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserSchemaValidation {
 @Test
    public void validateUserSchema(){
     Response response = new AuthService().login(null, null, LoginAs.ADMIN);
     SchemaValidation.validate(response, "userSchema", "data.user");
 }

}
