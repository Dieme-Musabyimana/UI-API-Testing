package apitests.admin;

import api.constants.Status;
import api.services.AdminService;
import api.utils.Expected;
import api.utils.Faker;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCoupon {

    @Test
    public void createCouponAsAnAdmin(){
        String couponCode = Faker.generateCouponCode();
        Response response = new AdminService().createCoupon(couponCode, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.CREATED);
    }
    @Test
    public void createCouponNotAsAnAdmin(){
        String couponCode = Faker.generateCouponCode();
        Response response = new AdminService().createCoupon(couponCode, LoginAs.CUSTOMER);
        Assert.assertEquals(response.statusCode(), Status.FORBIDDEN);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.PERMISSION_ISSUE);
        Assert.assertNull(response.jsonPath().get("data"));
    }
    @Test
    public void createCouponWithoutLogin(){
        String couponCode = Faker.generateCouponCode();
        Response response = new AdminService().createCoupon(couponCode, LoginAs.NONE);
        Assert.assertEquals(response.statusCode(), Status.UNAUTHORIZED);
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
        Assert.assertNull(response.jsonPath().get("data"));
    }
}
