package apitests.CartAPI;

import api.constants.Status;
import api.services.CartService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductToCart {

    @Test
    public void addProductToCart(){
        Response response = new CartService().addToCart();
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.ADDED);
    }
}
