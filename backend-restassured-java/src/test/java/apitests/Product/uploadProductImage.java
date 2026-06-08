package apitests.Product;

import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.services.ProductService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class uploadProductImage extends BaseAPI {

    @Test
    public void uploadProductImageTest(){
        Response response = new ProductService().uploadProductImage();
        Assert.assertEquals(response.statusCode(), StatusCodes.CREATED);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
    }
}
