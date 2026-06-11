package apitests.CartAPI;

import api.constants.Status;
import api.services.CartService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SaveItemForLater {

    @Test
    public void saveCartForLaterUser(){
        Response response = new CartService().saveForLaterUse();
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertEquals(response.jsonPath().get("message"), Expected.SAVED);
    }

}
