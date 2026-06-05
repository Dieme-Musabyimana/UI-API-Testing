package apitests.Product;
import api.constants.StatusCodes;
import api.services.AuthService;
import api.services.ProductService;
import api.utils.Config;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteProduct {
    AuthService authService;
    ProductService productService;
    @BeforeMethod
    public void setUp(){
        this.authService = new AuthService();
        this.productService = new ProductService();
    }

    @Test
    public void loginAndDeleteProduct(){
        Response response = productService.deleteProduct(authService.getLoginToken(), Config.getProductId());
        Assert.assertEquals(response.statusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.DELETED);
    }

    @Test
    public void deleteUnExistingId(){
        Response response = productService.deleteProduct(authService.getLoginToken(), Config.getUnExistingId());
        Assert.assertEquals(response.statusCode(), StatusCodes.NOT_FOUND);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.NOT_FOUND);
    }
    @Test
    public void deleteProductWithoutLogin(){
        Response response = productService.deleteProduct("", Config.getIdToDelete());
        Assert.assertEquals(response.statusCode(), StatusCodes.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);

    }
}
