package apitests.Product;

import api.constants.StatusCodes;
import api.routes.Routes;
import api.services.AuthService;
import api.services.ProductService;
import api.utils.Expectations;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateProduct {

@Test

public void updateProduct(){
    Response response = new ProductService().updateProduct(Routes.UPDATE_PRODUCT, new AuthService().getLoginToken());

    Assert.assertEquals(response.statusCode(), StatusCodes.OK);
    Assert.assertTrue(response.jsonPath().getBoolean("success"));
    Assert.assertEquals(response.jsonPath().getString("message"), Expectations.PRODUCT_UPDATED);
    Assert.assertEquals(response.jsonPath().getString("data.name"), ProductService.newProductName);
}
}
