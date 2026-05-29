package api.base;

import api.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeSuite;

public class BaseAPI {

    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;
    protected static RequestSpecification multipartRequestSpec;

    @BeforeSuite
    public void beforeSuite() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUri())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.getBaseUri())
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();
        }
        return requestSpec;
    }

    public static RequestSpecification getMultipartRequestSpec() {
        if (multipartRequestSpec == null) {
            multipartRequestSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.getBaseUri())
                    .setContentType(ContentType.MULTIPART)
                    .log(LogDetail.ALL)
                    .build();
        }
        return multipartRequestSpec;
    }

    public static ResponseSpecification getResponseSpec() {
        if (responseSpec == null) {
            responseSpec = new ResponseSpecBuilder()
                    .log(LogDetail.ALL)
                    .build();
        }
        return responseSpec;
    }
}