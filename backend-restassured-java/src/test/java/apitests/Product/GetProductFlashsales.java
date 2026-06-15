package apitests.Product;

import api.constants.Status;
import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetProductFlashsales {

    @Test
    public void getProductFlashSalesTest(){
        Response response = new ProductService().getProductFlashSales(LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
    }
}
