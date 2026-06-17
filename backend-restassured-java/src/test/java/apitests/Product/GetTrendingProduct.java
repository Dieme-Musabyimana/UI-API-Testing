package apitests.Product;

import api.constants.Status;
import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTrendingProduct {

    @Test
    public void getTrendingProductTest(){
        Response response = new ProductService().getTrendingProduct(LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertNotEquals(response.jsonPath().getList("data").size(),0);
        Assert.assertNotNull(response.jsonPath().getString("data[0].id"));
        Assert.assertNotNull(response.jsonPath().getString("data[0].name"));
        Assert.assertNotNull(response.jsonPath().getString("data[0].slug"));
        Assert.assertNotNull(response.jsonPath().getString("data[0].description"));
    }
}
