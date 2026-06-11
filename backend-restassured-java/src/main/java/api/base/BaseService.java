package api.base;

import api.utils.TokenManager;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BaseService {

    private String getAuthToken() {
        return TokenManager.getToken();
    }

    private RequestSpecification getRequestSpec() {
        return BaseAPI.getRequestSpec();
    }

    public Response sendPost(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .post(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendGet(String endpoint) {
        return given()
                .spec(getRequestSpec())
                .when()
                .get(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendGetWithAuth(String endpoint, Map<String, Object> queryParams) {
        var requestSpec = given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + getAuthToken());
        if (queryParams != null && !queryParams.isEmpty()) {
            requestSpec.queryParams(queryParams);
        }
        return requestSpec.when()
                .get(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendPostWithAuth(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + getAuthToken())
                .body(body)
                .when()
                .post(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendPut(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .put(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendPutWithAuth(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + getAuthToken())
                .body(body)
                .when()
                .put(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendPatchWithAuth(String endpoint, Object body, Map<String, Object> pathParams) {
        var requestSpec = given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + getAuthToken());
        if (pathParams != null && !pathParams.isEmpty()) {
            requestSpec.queryParams(pathParams);
        }
        if (body != null) {
            if (body instanceof String && ((String) body).trim().isEmpty()) {
            } else {
                requestSpec.body(body);
            }
        }
        return requestSpec
                .body(body)
                .when()
                .patch(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendDelete(String endpoint){
        return given()
                .spec(getRequestSpec())
                .when()
                .delete(endpoint)
                .then().log().all()
                .extract().response();
    }

    protected Response sendDeleteWithAuth(String endpoint) {
        return given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + getAuthToken())
                .when()
                .delete(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendPostMultipartWithAuth(String endpoint, File file, String controlName) {
        return given()
                .spec(BaseAPI.getMultipartRequestSpec())
                .header("Authorization", "Bearer " + getAuthToken())
                .multiPart(controlName, file)
                .when()
                .post(endpoint)
                .then().log().all()
                .extract()
                .response();
    }
}