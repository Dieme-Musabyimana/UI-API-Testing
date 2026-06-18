package apitests.ordersAPi;

import api.constants.Status;
import api.services.OrderService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestReturn {
    @Test
    public void requestReturnForDeliveredOder(){
        Response response = new OrderService().requestReturn("DELIVERED");
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.RETURN_SUBMITTED);
    }

    @Test
    public void requestReturnForPendingOrder(){
        Response response = new OrderService().requestReturn("PENDING");
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.RETURN_REFUSED);
    }


    @Test
    public void requestReturnForCancelledOrder(){
        Response response = new OrderService().requestReturn("CANCELLED");
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.RETURN_REFUSED);
    }
    @Test
    public void requestReturnForConfirmedOrder(){
        Response response = new OrderService().requestReturn("CONFIRMED");
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.RETURN_REFUSED);
    }
}
