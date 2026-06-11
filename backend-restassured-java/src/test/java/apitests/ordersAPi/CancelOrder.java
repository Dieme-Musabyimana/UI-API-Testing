package apitests.ordersAPi;

import api.constants.Status;
import api.services.OrderService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CancelOrder {

    @Test
    public void cancelOrderWithWrongId(){
        String id = "824ba5ed-f8e5-4b08-ae51-a8dbe459c9";
        Response response = new OrderService().getSingleOrder(id);
        Assert.assertEquals(response.statusCode(), Status.NOT_FOUND);
    }
}
