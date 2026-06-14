package apitests.CartAPI;

import api.constants.Status;
import api.services.CartService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplyCoupon {

    @Test
    public  void ApplyCoupon(){
        Response response = new CartService().applyCoupon();
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertTrue(response.jsonPath().getString("message").contains(Expected.APPLIED));
        Assert.assertFalse(response.jsonPath().getString("data.coupon.id").isEmpty());
        Assert.assertEquals(response.jsonPath().getString("data.coupon.code"), "SAVE15NOW");
        Assert.assertEquals(response.jsonPath().getString("data.coupon.discountType"), "PERCENTAGE");
        Assert.assertFalse(response.jsonPath().getString("data.coupon.discountValue").isEmpty());
    }
}
