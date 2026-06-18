package apitests.Product;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.ProductService;
import api.utils.LoginAs;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class uploadProductImage extends BaseAPI {

    @Test
    public void uploadProductImageTest(){
        String productId = new ProductService().getProductParams(LoginAs.ADMIN).get("productId").toString();
        Response response = new ProductService().uploadProductImage(productId);
        Assert.assertEquals(response.statusCode(), Status.CREATED, Message.DATA_TYPE_MISMATCH);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
    }
}
