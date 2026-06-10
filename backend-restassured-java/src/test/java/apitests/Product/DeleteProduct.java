package apitests.Product;

import api.constants.StatusCodes;
import api.services.ProductService;
import api.utils.Config;
import api.utils.Expected;
import api.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteProduct {
    private ProductService productService;
    private TokenManager tokenManager;

    @BeforeClass
    public void setUp() {
        this.productService = new ProductService();
        this.tokenManager = new TokenManager();
    }

    @Test
    public void loginAndDeleteProduct(){
        String token = tokenManager.getToken();
        Response res = productService.createProduct(token);
        String id = res.jsonPath().getString("data.id");
        Response response = productService.deleteProduct(token, id);
        String actualMessage = response.jsonPath().getString("message");

        Assert.assertEquals(response.statusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        assertThat(actualMessage, either(is(Expected.DELETED)).or(is(Expected.DEACTIVATED)));    }

    @Test
    public void deleteUnExistingId(){
        String token = tokenManager.getToken();
        Response response = productService.deleteProduct(token, Config.getUnExistingId());
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