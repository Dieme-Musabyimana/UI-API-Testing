package apitests.Product;

import api.constants.Status;
import api.services.ProductService;
import api.utils.Config;
import api.utils.Expected;
import api.utils.LoginAs;
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
        Response res = productService.createProduct(LoginAs.ADMIN);
        String id = res.jsonPath().getString("data.id");
        String slug = res.jsonPath().getString("data.slug");
        Response response = productService.deleteProduct(id, LoginAs.ADMIN);
        String actualMessage = response.jsonPath().getString("message");


        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        assertThat(actualMessage, either(is(Expected.DELETED)).or(is(Expected.DEACTIVATED)));
        Response res2= productService.getSingleProduct(slug, LoginAs.ADMIN);
        Assert.assertTrue(res2.jsonPath().getString("message").contains(Expected.NOT_FOUND));


    }

    @Test
    public void deleteUnExistingId(){
        Response response = productService.deleteProduct(Config.getUnExistingId(), LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.NOT_FOUND);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertTrue(response.jsonPath().getString("message").contains(Expected.NOT_FOUND));
    }

    @Test
    public void deleteProductWithoutLogin(){
        Response response = productService.deleteProduct(Config.getIdToDelete(), LoginAs.NONE);
        Assert.assertEquals(response.statusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
    }
}