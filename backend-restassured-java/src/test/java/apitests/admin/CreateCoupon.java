package apitests.admin;

import api.constants.StatusCodes;
import api.services.AdminService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCoupon {

@Test
    public void createCoupon(){
    Response response = new AdminService().createCoupon();
    Assert.assertEquals(response.statusCode(), StatusCodes.CREATED);

}
}
