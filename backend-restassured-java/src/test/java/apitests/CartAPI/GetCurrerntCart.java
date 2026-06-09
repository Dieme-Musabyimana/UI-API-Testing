package apitests.CartAPI;

import api.constants.StatusCodes;
import api.services.CartService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetCurrerntCart {

    @Test

    public void getCurrentCartTest(){
        Response response = new CartService().getCurrentCart();
        Assert.assertEquals(response.statusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
    }
}
