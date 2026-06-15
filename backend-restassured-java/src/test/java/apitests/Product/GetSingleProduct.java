package apitests.Product;

import api.constants.Status;
import api.routes.Routes;
import api.services.ProductService;
import api.utils.Config;
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
        String slug = Config.getProductSlug();
        Response response = productService.getProducts(Routes.SINGLE_PRODUCT, slug);
        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("data.slug"), productSlug);
    }
}
