package apitests.wishlist;

import api.constants.Status;
import api.services.ProductService;
import api.utils.Faker;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddToWishlist {
    ProductService productService;
    @BeforeMethod
    public void setProductService(){
        this.productService = new ProductService();
    }

    @Test
    public void addProductToWishlistWithAsAnAdmin(){
        String addedId = productService.getProductParams(LoginAs.ADMIN).get("productId").toString();
        Response response = productService.addProductToWishlist(addedId, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.CREATED);
        Response response3 = productService.getWishList(LoginAs.ADMIN);
        List<String> productIds = response3.jsonPath().getList("data.productId");
        Assert.assertTrue(productIds.contains(addedId));
    }

    @Test
    public void addProductToWishlistWithAsCustomer(){
        String addedId = productService.getProductParams(LoginAs.CUSTOMER).get("productId").toString();
        Response response = productService.addProductToWishlist(addedId, LoginAs.CUSTOMER);
        Assert.assertEquals(response.statusCode(), Status.CREATED);
        Response response3 = productService.getWishList(LoginAs.CUSTOMER);
        List<String> productIds = response3.jsonPath().getList("data.productId");
        Assert.assertTrue(productIds.contains(addedId));
    }
    @Test
    public void addProductToWishlistWithoutLogin(){
        String id = productService.getProductParams(LoginAs.CUSTOMER).get("productId").toString();
        Response response = productService.addProductToWishlist(id, LoginAs.NONE);
        Assert.assertEquals(response.statusCode(), Status.UNAUTHORIZED);
    }
    @Test
    public void addUnexistingProductToWishlist(){
        String productId = Faker.getRandomId();
        Response response = productService.addProductToWishlist(productId, LoginAs.NONE);
        Assert.assertEquals(response.statusCode(), Status.UNAUTHORIZED);
    }
}
