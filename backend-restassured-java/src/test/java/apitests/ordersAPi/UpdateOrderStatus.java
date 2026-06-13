package apitests.ordersAPi;

import api.constants.Status;
import api.services.OrderService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class UpdateOrderStatus {
    OrderService orderService;

    @BeforeMethod
    public void setUp(){
        this.orderService = new OrderService();
    }


    @Test
    public void updateOrderStatusAsDelivered(){
        Map<String, Object> payload = orderService.getBody("DELIVERED");
        Response response = orderService.updateOderStatus(payload);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Order status updated");
        Assert.assertEquals(response.jsonPath().getString("data.status"), payload.get("status"));
        Assert.assertEquals(response.jsonPath().getString("data.trackingNumber"), payload.get("trackingNumber"));
    }

    @Test
    public void updateOrderStatusAsShipped(){
        Map<String, Object> payload = orderService.getBody("SHIPPED");
        Response response = orderService.updateOderStatus(payload);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Order status updated");
        Assert.assertEquals(response.jsonPath().getString("data.status"), payload.get("status"));
        Assert.assertEquals(response.jsonPath().getString("data.trackingNumber"), payload.get("trackingNumber"));
    }

    @Test
    public void updateOrderStatusAsCancelled(){
        Map<String, Object> payload = orderService.getBody("CANCELLED");
        Response response = orderService.updateOderStatus(payload);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Order status updated");
        Assert.assertEquals(response.jsonPath().getString("data.status"), payload.get("status"));
        Assert.assertEquals(response.jsonPath().getString("data.trackingNumber"), payload.get("trackingNumber"));
    }

    @Test
    public void updateOrderStatusAsReturned(){
        Map<String, Object> payload = orderService.getBody("RETURNED");
        Response response = orderService.updateOderStatus(payload);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Order status updated");
        Assert.assertEquals(response.jsonPath().getString("data.status"), payload.get("status"));
        Assert.assertEquals(response.jsonPath().getString("data.trackingNumber"), payload.get("trackingNumber"));
    }

    @Test
    public void updateOrderStatusAsConfirmed(){
        Map<String, Object> payload = orderService.getBody("CONFIRMED");
        Response response = orderService.updateOderStatus(payload);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Order status updated");
        Assert.assertEquals(response.jsonPath().getString("data.status"), payload.get("status"));
        Assert.assertEquals(response.jsonPath().getString("data.trackingNumber"), payload.get("trackingNumber"));
    }


}
