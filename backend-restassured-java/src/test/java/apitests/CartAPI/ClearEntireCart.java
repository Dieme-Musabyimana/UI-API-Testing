package apitests.CartAPI;

import api.constants.Status;
import api.services.CartService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
public class ClearEntireCart {

    @Test
    public void clearEntireCartTest(){
        Response response = new CartService().clearEntireCart();
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.CART_CLEARED);
        Assert.assertTrue(response.jsonPath().getObject("data", Map.class).isEmpty());
    }
}
