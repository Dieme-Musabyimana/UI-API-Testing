package apitests.Product;

import api.constants.StatusCodes;
import api.routes.Routes;
import api.services.AuthService;
import api.services.ProductService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;
import static api.services.ProductService.newProductName;

public class UpdateProduct {
AuthService authService;
    @BeforeMethod
    public void setUp() {
        this.authService = new AuthService();
    }
@Test
public void loginAsAdminAndUpdateProduct(){
    Response response = new ProductService().updateProduct(Routes.UPDATE_PRODUCT, Map.of("name", newProductName), authService.getLoginToken());
    Assert.assertEquals(response.statusCode(), StatusCodes.OK);
    Assert.assertTrue(response.jsonPath().getBoolean("success"));
    Assert.assertEquals(response.jsonPath().getString("message"), Expected.PRODUCT_UPDATED);
    Assert.assertEquals(response.jsonPath().getString("data.name"), newProductName);
}
@Test
public void updateProductWithoutLogin() {
    Response response = new ProductService().updateProduct(Routes.UPDATE_PRODUCT, Map.of("name",newProductName), "");
    Assert.assertEquals(response.statusCode(), StatusCodes.UNAUTHORIZED);
    Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
}
@Test
    public void updateProductWithEmptyName(){
    Response response = new ProductService().updateProduct(Routes.UPDATE_PRODUCT, Map.of("name", " "), authService.getLoginToken());
    Assert.assertEquals(response.statusCode(), StatusCodes.BAD_REQUEST);
}
}

