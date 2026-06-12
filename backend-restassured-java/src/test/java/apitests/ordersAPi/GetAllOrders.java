package apitests.ordersAPi;

import api.constants.Status;
import api.services.OrderService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllOrders {

    @Test
    public void getAllOrders(){
        Response response = new OrderService().getAllOrders();
        Assert.assertEquals(response.statusCode(), Status.OK);
    }
}
