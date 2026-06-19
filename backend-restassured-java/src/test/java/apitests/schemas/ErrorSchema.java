package apitests.schemas;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import api.constants.Status;
import api.routes.Routes;
import api.services.ProductService;
import api.utils.Config;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ErrorSchema {

    @Test
    public void validateErrorSchemaForValidUrl(){
        Response response = new ProductService().getActiveBanners(LoginAs.ADMIN);
        String path = response.jsonPath().getString("pagination");
        SchemaValidation.validate(response,"errorSchema", null);
    }

    @Test
    public void validateErrorSchemaForInvalidUrl(){
        String route = Routes.BANNERS + "/invalid";

    }
}
