package apitests.schemas;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

public class ErrorSchema {

    @Test
    public void validateErrorSchema(){
        Response response = new ProductService().getActiveBanners(LoginAs.ADMIN);
        File schemaFile = new File("backend-restassured-java/src/main/resources/schemas/ErrorSchemas.json");
        response.then().body(matchesJsonSchema(schemaFile));
    }
}
