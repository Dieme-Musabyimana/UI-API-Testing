package apitests.CartAPI;

import api.services.CartService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UpdateCartItemQuantity {

    @Test
    public void updateCartQuantity(){
        Response response = new CartService().updateQuantity();

    }
}
