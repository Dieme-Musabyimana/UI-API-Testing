package api.base;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseService {
    private RequestSpecification getRequestSpec() {
        return BaseAPI.getRequestSpec();
    }

    protected Response sendPost(String endpoint, Object body) {
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
    protected Response sendPatch(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .patch(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    protected Response sendDelete(String endpoint) {
        return given()
                .spec(getRequestSpec())
                .when()
                .delete(endpoint)
                .then().log().all()
                .extract()
                .response();
    }

    // Inside api.base.BaseService.java

    protected Response sendGetWithAuth(String endpoint, String token) {
        return given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint)
                .then().log().all()
                .extract()
                .response();
    }
    protected Response sendPutWithAuth(String endpoint, Object body, String token) {
        return given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .put(endpoint)
                .then().log().all()
                .extract()
                .response();
}

}