
package api.base;

import api.utils.TokenManager;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseService {

    private String getAuthToken() {
        return TokenManager.getToken();
    }

    private RequestSpecification getRequestSpec() {
        return BaseAPI.getRequestSpec();
    }

    protected Response sendGet(String endpoint, Map<String, Object> pathParams, Map<String, Object> queryParams, boolean login) {
        var requestSpec = given().spec(getRequestSpec());

        if (login) {
            requestSpec.header("Authorization", "Bearer " + getAuthToken());
        }
        if (queryParams != null && !queryParams.isEmpty()) {
            requestSpec.queryParams(queryParams);
        }
        if (pathParams != null && !pathParams.isEmpty()) {
            requestSpec.pathParams(pathParams);
        }
        return requestSpec.when()
                .get(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    public Response sendPost(String endpoint, Object body, Map<String, Object> pathParams, boolean login) {
        var requestSpec = given()
                .spec(getRequestSpec())
                .body(body);
        if(pathParams != null && !pathParams.isEmpty()){
            requestSpec.pathParams(pathParams);
        }
        if (login) {
            requestSpec.header("Authorization", "Bearer " + getAuthToken());
        }

        return requestSpec.when()
                .post(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendPut(String endpoint, Object body, Map<String, Object> pathParams, boolean login) {
        var requestSpec = given().spec(getRequestSpec());

        if (login) {
            requestSpec.header("Authorization", "Bearer " + getAuthToken());
        }
        requestSpec.body(body);

        if (pathParams != null && !pathParams.isEmpty()) {
            requestSpec.pathParams(pathParams);
        }
        return requestSpec
                .when()
                .put(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendPatch(String endpoint, Object body, Map<String, Object> pathParams, Map<String, Object> queryParams, boolean login) {
        var requestSpec = given().spec(getRequestSpec());

        if (login) {
            requestSpec.header("Authorization", "Bearer " + getAuthToken());
        }
        if (pathParams != null && !pathParams.isEmpty()) {
            requestSpec.pathParams(pathParams);
        }
        if (queryParams != null && !queryParams.isEmpty()) {
            requestSpec.queryParams(queryParams);
        }
        if (body != null) {
            requestSpec.body(body);
        }
        return requestSpec
                .when()
                .patch(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendDelete(String endpoint, Map<String, Object> pathParams, boolean login) {
        var requestSpec = given().spec(getRequestSpec());

        if (login) {
            requestSpec.header("Authorization", "Bearer " + getAuthToken());
        }
        if (pathParams != null && !pathParams.isEmpty()) {
            requestSpec.pathParams(pathParams);
        }
        return requestSpec
                .when()
                .delete(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendPostMultipartWithAuth(String endpoint, File file, String controlName, Map<String, Object> pathParams) {
        var requestSpec = given();

        requestSpec.header("Authorization", "Bearer " + getAuthToken());

        if (file != null && file.isFile()) {
            requestSpec.spec(BaseAPI.getMultipartRequestSpec());
        } else {
            requestSpec.spec(getRequestSpec());
        }

        if (pathParams != null && !pathParams.isEmpty()) {
            requestSpec.pathParams(pathParams);
        }

        if (file != null && file.exists()) {
            requestSpec.multiPart(controlName, file);
        }
        return requestSpec
                .when()
                .post(endpoint)
                .then().log().all()
                .extract()
                .response();
    }
}