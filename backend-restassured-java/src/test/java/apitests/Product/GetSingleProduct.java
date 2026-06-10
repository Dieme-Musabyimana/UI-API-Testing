package apitests.Product;

import api.constants.StatusCodes;
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

        String productSlug = new ProductService().getProductSlug();
        Response response = productService.getProducts(Routes.SINGLE_PRODUCT+productSlug);
        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("data.slug"), productSlug);
    }
}
