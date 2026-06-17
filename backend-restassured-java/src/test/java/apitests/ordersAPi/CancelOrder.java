package apitests.ordersAPi;

import api.constants.Status;
import api.services.CartService;
import api.services.OrderService;
import api.utils.Expected;
import api.utils.Faker;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CancelOrder {

    @Test
    public void cancelOrder(){
        new CartService().addToCart(LoginAs.ADMIN);
        String id = new OrderService().placeOder("CASH_ON_DELIVERY").jsonPath().getString("data.id");
        Response response = new OrderService().cancelOrder(id);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.CANCELLED);

        Response response2 = new OrderService().getSingleOrder(id, LoginAs.ADMIN);
        Assert.assertEquals(response2.jsonPath().getString("data.id"), id);
        Assert.assertEquals(response2.jsonPath().getString("data.status"), "CANCELLED");
    }

    @Test
    public void cancelUnexistingOrder(){
        String id = Faker.getRandomId();
        Response response = new OrderService().getSingleOrder(id, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.NOT_FOUND);
    }
}
