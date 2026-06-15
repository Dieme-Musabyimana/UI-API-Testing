package apitests.admin;

import api.constants.Status;
import api.services.AdminService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCoupon {

@Test
    public void createCoupon(String couponCode, LoginAs loginAs){
    Response response = new AdminService().createCoupon(couponCode, loginAs);
    Assert.assertEquals(response.statusCode(), Status.CREATED);
}
}
