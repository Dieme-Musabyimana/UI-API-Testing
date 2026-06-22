package apitests.schemas;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat; // Add this import

import api.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import java.io.File;

public class SchemaValidation {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void validate(Response response,
                                String schemaName,
                                String jsonPath) {

        File file = new File(Config.getSchemaPath() + schemaName + ".json");

        if (!file.exists()) {
            throw new RuntimeException("Schema file not found: " + file.getAbsolutePath());
        }

        try {
            if (jsonPath == null || jsonPath.trim().isEmpty()) {
                response.then()
                        .body(matchesJsonSchema(file));
            }
            else {
                Object node = response.jsonPath().get(jsonPath);

                if (node == null) {
                    throw new RuntimeException("JSON path not found: " + jsonPath);
                }
                String json = mapper.writeValueAsString(node);
                assertThat(json, matchesJsonSchema(file));
            }
            System.out.println("Schema path = " + file.getAbsolutePath());

        } catch (Throwable e) {
            throw new RuntimeException("Schema validation failed: " + schemaName, e);
        }
    }
}