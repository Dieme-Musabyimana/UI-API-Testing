package apitests.ordersAPi;

import api.constants.Status;
import api.services.CartService;
import api.services.OrderService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CancelOrder {

    @Test
    public void cancelOrder(){
        new CartService().addToCart(true);
        String id = new OrderService().placeOder("CASH_ON_DELIVERY").jsonPath().getString("data.id");
        Response response = new OrderService().cancelOrder(id);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.CANCELLED);

        Response response2 = new OrderService().getSingleOrder(id, true);
        Assert.assertEquals(response2.jsonPath().getString("data.id"), id);
        Assert.assertEquals(response2.jsonPath().getString("data.status"), "CANCELLED");
    }

    @Test
    public void cancelUnexistingOrder(){
        String id = "824ba5ed-f8e5-4b08-ae51-a8de459c9";
        Response response = new OrderService().getSingleOrder(id, true);
        Assert.assertEquals(response.statusCode(), Status.NOT_FOUND);
    }
}
