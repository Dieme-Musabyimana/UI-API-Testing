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
        String route = Routes.BANNERS + "/invalid";

        Response response = new ProductService().validateErrorSchema(Routes.BANNERS, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.OK);
        File schemaFile = new File(Config.getErrorSchema());
        response.then().body(matchesJsonSchema(schemaFile));
    }

    @Test
    public void validateErrorSchemaForInvalidUrl(){
        String route = Routes.BANNERS + "/invalid";
        Response response = new ProductService().validateErrorSchema(route, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.NOT_FOUND);
        File schemaFile = new File(Config.getErrorSchema());
        response.then().body(matchesJsonSchema(schemaFile));
    }
}
