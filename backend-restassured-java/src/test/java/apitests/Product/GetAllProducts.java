package apitests.Product;

import api.constants.StatusCodes;
import api.routes.Routes;
import api.services.ProductService;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class GetAllProducts {

    @Test
    public void getAllProducts(){
        Response response = new ProductService().getProducts(Routes.PRODUCT);
        response.then().statusCode(StatusCodes.OK);
        response.then().body("success", equalTo(true));
        response.then().body("message", equalTo("Success"));
        response.then().body("data", notNullValue());
        response.then().body("data.size()", greaterThan(0));
    }
}
