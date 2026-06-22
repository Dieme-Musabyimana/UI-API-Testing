package apitests.schemas;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;

import api.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.File;

public class SchemaValidation {

    private static final ObjectMapper mapper = new ObjectMapper();


    public static void validate(Response response,
                                String schemaName,
                                String jsonPath,
                                String message) {

        File file = new File(
                Config.getSchemaPath() + schemaName + ".json"
        );

        if (!file.exists()) {
            throw new RuntimeException(
                    "Schema file not found: " + file.getAbsolutePath()
            );
        }

        String errorMessage =
                message != null && !message.trim().isEmpty()
                        ? message
                        : "Schema validation failed for schema: " + schemaName;

        try {

            if (jsonPath == null || jsonPath.trim().isEmpty()) {
                String json = response.asString();

                assertThat(
                        message,
                        json,
                        matchesJsonSchema(file));
            }

            else {

                Object node = response.jsonPath().get(jsonPath);

                if (node == null) {
                    throw new RuntimeException(
                            "JSON path not found: " + jsonPath
                    );
                }

                String json = mapper.writeValueAsString(node);

                assertThat(
                        errorMessage,
                        json,
                        matchesJsonSchema(file)
                );
            }

            System.out.println(
                    "Schema validation passed: " + schemaName
            );
            System.out.println(
                    "Schema path: " + file.getAbsolutePath()
            );

        } catch (Throwable e) {

            throw new RuntimeException(
                    errorMessage,
                    e
            );
        }
    }
}