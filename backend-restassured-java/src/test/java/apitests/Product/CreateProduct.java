package apitests.Product;

import api.constants.StatusCodes;
import api.payloads.RequestPayloads;
import api.services.AuthService;
import api.services.ProductService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateProduct {
    @Test
    public void createProductTest() {
        String token = new AuthService().getLoginToken();
        Response response = new ProductService().createProduct(token);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED);
        Assert.assertEquals(response.jsonPath().getString("data.name"), new RequestPayloads().getName());
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.CREATED);
        Assert.assertEquals(response.jsonPath().getString("data.variants[0].sku"), new RequestPayloads().getSku());
        Assert.assertNotNull(response.jsonPath().getString("data.id"));
    }
}
