package apitests.Product;

import api.constants.Status;
import api.services.ProductService;
import api.utils.Expected;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static api.services.ProductService.newProductName;

public class UpdateProduct {

@Test
public void loginAsAdminAndUpdateProduct(){
    Response response = new ProductService().updateProduct(LoginAs.ADMIN);
    Assert.assertEquals(response.statusCode(), Status.OK);
    Assert.assertTrue(response.jsonPath().getBoolean("success"));
    Assert.assertEquals(response.jsonPath().getString("message"), Expected.PRODUCT_UPDATED);
    Assert.assertEquals(response.jsonPath().getString("data.name"), newProductName);
}
@Test
public void updateProductWithoutLogin() {
    Response response = new ProductService().updateProduct(LoginAs.NONE);
    Assert.assertEquals(response.statusCode(), Status.UNAUTHORIZED);
    Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
}
@Test
    public void updateProductWithEmptyName(){
    Response response = new ProductService().updateProduct(LoginAs.ADMIN);
    Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST);
}
}

