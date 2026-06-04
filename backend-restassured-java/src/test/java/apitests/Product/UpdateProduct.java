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

import java.util.Map;

import static api.services.ProductService.newProductName;

public class UpdateProduct {

@Test

public void loginAsAdminAndUpdateProduct(){
    Response response = new ProductService().updateProduct(Routes.UPDATE_PRODUCT, Map.of("name", newProductName), new AuthService().getLoginToken());

    Assert.assertEquals(response.statusCode(), StatusCodes.OK);
    Assert.assertTrue(response.jsonPath().getBoolean("success"));
    Assert.assertEquals(response.jsonPath().getString("message"), Expectations.PRODUCT_UPDATED);
    Assert.assertEquals(response.jsonPath().getString("data.name"), newProductName);
}
@Test
public void updateProducWithoutLogin() {
    Response response = new ProductService().updateProduct(Routes.UPDATE_PRODUCT, Map.of("name",newProductName), "");
    Assert.assertEquals(response.statusCode(), StatusCodes.UNAUTHORIZED);
    Assert.assertEquals(response.jsonPath().getString("message"), Expectations.AUTHENTICATION_ERROR);
}
}

