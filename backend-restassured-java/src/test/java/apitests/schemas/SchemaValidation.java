package apitests.schemas;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import api.utils.Config;
import io.restassured.response.Response;

import java.io.File;

public class SchemaValidation {

    public static void validate(Response response, String schemaName, String jsonPath) {

        File file = new File(Config.getSchemaPath() + schemaName + ".json");

        if (jsonPath == null || jsonPath.trim().isEmpty()) {
            response.then().assertThat().body(matchesJsonSchema(file));
        } else {
            String extractedJson = response.jsonPath().getString(jsonPath);


                    given()
                    .body(extractedJson)
                    .then()
                    .body(matchesJsonSchema(file));
        }
    }
}
