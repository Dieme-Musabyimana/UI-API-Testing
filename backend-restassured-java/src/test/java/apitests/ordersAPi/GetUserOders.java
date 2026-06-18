package apitests.ordersAPi;

import api.constants.Status;
import api.services.OrderService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GetUserOders {
    OrderService orderService;

    @BeforeMethod
    public void setOrderService(){
        orderService = new OrderService();
    }

    private Response validateOrdersByStatusAndPage(String expectedStatus, int expectedPage) {
        Response response = orderService.getUserOder(expectedStatus, expectedPage);

        Assert.assertEquals(response.statusCode(), Status.OK);

        List<String> statuses = response.jsonPath().getList("data.status");
        Assert.assertFalse(statuses.isEmpty(), "No orders found matching status: " + expectedStatus + " on page " + expectedPage);

        for (String status : statuses) {
            Assert.assertEquals(status, expectedStatus);
        }

        int actualPage = response.jsonPath().getInt("pagination.page");
        Assert.assertEquals(actualPage, expectedPage);
        return response;
    }

    @Test
    public void getPendingOrders(){
        validateOrdersByStatusAndPage("PENDING", 2);
        Assert.assertEquals(validateOrdersByStatusAndPage("PENDING", 2).jsonPath().getString("message"), "Success");
    }

    @Test
    public void getConfirmedOrders(){
        validateOrdersByStatusAndPage("CONFIRMED", 1);
    }

    @Test
    public void getShippedOrders(){
        validateOrdersByStatusAndPage("SHIPPED", 1);
    }

    @Test
    public void getDeliveredOrders(){
        validateOrdersByStatusAndPage("DELIVERED", 1);
    }

    @Test
    public void getCancelledOrders(){
        validateOrdersByStatusAndPage("CANCELLED", 1);
    }

    @Test
    public void getReturnedOrders(){
        validateOrdersByStatusAndPage("RETURNED", 1);
    }
}