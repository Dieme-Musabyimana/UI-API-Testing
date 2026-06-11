package apitests.CartAPI;

import api.constants.Status;
import api.services.CartService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateCartItemQuantity {

    @Test
    public void updateCartQuantity(){
        Response response = new CartService().updateQuantity();
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.UPDATED);

    }
}
