package apitests.schemas;

import api.services.AuthService;
import api.services.UserService;
import api.utils.Config;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserSchemaValidation {
 @Test
    public void validateUserSchema(){
     AuthService authService = new AuthService();
     String email = Config.getAdminLoginEmail();
     String password = Config.getAdminLoginPassword();
     Response response = authService.login(email, password,LoginAs.ADMIN);
     SchemaValidation.validate(response, "userSchema", "data.user");
 }

}
