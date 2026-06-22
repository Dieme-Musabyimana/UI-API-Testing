package apitests.schemas;

import api.services.AuthService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class AuthResponseSchema {

    @Test
    public void authResSchemaValidation(){
        Response response = new AuthService().login(null, null, LoginAs.ADMIN);
        SchemaValidation.validate(response, "authResSchema", "data");
    }
}
