package apitests.ordersAPi;

import api.constants.Status;
import api.services.OrderService;
import api.utils.Expected;
import api.utils.Faker;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSingleOrder {

    @Test
    public void getSingleOrder(){
        String id = "824ba5ed-f8e5-4b08-ae51-a8dbe459c932";
        Response response = new OrderService().getSingleOrder(id, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertEquals(response.jsonPath().getString("data.id"), id);
    }

    @Test
    public void getUnexistingOderId(){
        String id = Faker.getRandomId();
        Response response = new OrderService().getSingleOrder(id, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.NOT_FOUND);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.NO_ORDER);

    }
}
