package apitests.Product;

import api.routes.Routes;
import api.services.ProductService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetSingleProduct {
    ProductService productService;

    @BeforeMethod

    public void setUp(){
        this.productService = new ProductService();
    }


    @Test
    public void getSingleProduct(){
        Response response = productService.getProducts(Routes.SINGLE_PRODUCT);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("data[0].id"), "89c903cd-df0d-441a-9a68-a0b8465a3109");
        Assert.assertEquals(response.jsonPath().getString("data[0].name"), "Clear PVC Stadium Tote Bag");
        Assert.assertEquals(response.jsonPath().getFloat("data[0].price"), 14.99f);
    }
}
