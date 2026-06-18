package apitests.ordersAPi;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.OrderService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceNewOrder extends BaseAPI {

    @Test

    public void placeOrder(){
        Response response = new OrderService().placeOder("CASH_ON_DELIVERY");
        Assert.assertEquals(response.statusCode(), Status.CREATED);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
    }
}
