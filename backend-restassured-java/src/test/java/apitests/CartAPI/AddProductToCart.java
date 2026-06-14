package apitests.CartAPI;

import api.constants.Status;
import api.services.CartService;
import api.utils.Expected;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductToCart {

    @Test
    public void addProductToCartWhileLoggedIn(){
        Response response = new CartService().addToCart(true);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.ADDED);
    }

    @Test public void addProductToCartWithoutLogIn(){
        Response response = new CartService().addToCart(false);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.ADDED, Message.GUEST_CART_ADDITION_BLOCKED);
    }
}
