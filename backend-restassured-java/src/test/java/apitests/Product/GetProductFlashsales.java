package apitests.Product;

import api.constants.StatusCodes;
import api.services.ProductService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetProductFlashsales {

    @Test
    public void getProductFlashSalesTest(){
        Response response = new ProductService().getProductFlashSales();
        Assert.assertEquals(response.statusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
    }
}
