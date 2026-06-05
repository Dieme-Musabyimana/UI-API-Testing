package apitests.Product;

import api.constants.StatusCodes;
import api.payloads.RequestPayloads;
import api.services.AuthService;
import api.services.ProductService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateProduct {


    @Test
    public void createProductTest() {
        String token = new AuthService().getLoginToken();
        Response response = new ProductService().createProduct(token);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED);
        Assert.assertEquals(response.jsonPath().getString("name"), );
    }
}
