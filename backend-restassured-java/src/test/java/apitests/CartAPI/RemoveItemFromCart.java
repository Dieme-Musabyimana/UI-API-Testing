package apitests.CartAPI;

import api.constants.Status;
import api.services.CartService;
import api.utils.Expected;
import api.utils.Faker;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveItemFromCart {

    @Test
    public void removeItemFromCart(){
        String id = new CartService().getCartItemId();
        Response response = new CartService().removeItemFromCart(id);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"),Expected.REMOVED);
    }
    @Test
    public void removeUnexistingItem(){
            String itemId = Faker.getRandomId();
            Response response = new CartService().removeItemFromCart(itemId);
            Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST);
            Assert.assertFalse(response.jsonPath().getBoolean("success"));
            Assert.assertFalse(response.jsonPath().getString("message").contains(Expected.REMOVED), Message.FAILED_TO_REJECT_NON_EXISTENT_ID);
    }

    @Test
    public void removingItemWithEmptyId(){
        Response response= new CartService().removeItemFromCart(" ");
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST);
        Assert.assertFalse(response.jsonPath().getBoolean("success"), Message.FAILED_TO_REJECT_BLANK_ID);


    }
}
