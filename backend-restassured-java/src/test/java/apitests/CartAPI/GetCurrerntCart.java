package apitests.CartAPI;

import api.constants.StatusCodes;
import api.services.CartService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetCurrerntCart {

    @Test
    public void clearEntireAndGetCurrentCart(){
        CartService cartService = new CartService();
        cartService.clearEntireCart();
        Response response = cartService.getCurrentCart();

        Assert.assertEquals(response.statusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertTrue(response.jsonPath().getList("data.items").isEmpty());
        Assert.assertEquals(response.jsonPath().getInt("data.subtotal"), 0);
        Assert.assertEquals(response.jsonPath().getInt("data.discount"), 0);
        Assert.assertEquals(response.jsonPath().getInt("data.total"), 0);
        Assert.assertEquals(response.jsonPath().getInt("data.itemCount"), 0);
    }

    @Test

    public void addAndGetCurrentCartTest(){
        CartService cartService = new CartService();
        cartService.addToCart();
        Response response = cartService.getCurrentCart();
        Assert.assertEquals(response.statusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertFalse(response.jsonPath().getList("data.items").isEmpty());
        Assert.assertTrue(response.jsonPath().getInt("data.subtotal") > 0);
        Assert.assertTrue(response.jsonPath().getInt("data.discount") > 0);
        Assert.assertTrue(response.jsonPath().getInt("data.total") > 0);
        Assert.assertTrue(response.jsonPath().getInt("data.itemCount") > 0);
    }
}
