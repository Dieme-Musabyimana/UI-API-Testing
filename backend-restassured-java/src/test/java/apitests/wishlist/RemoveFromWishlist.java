package apitests.wishlist;

import api.constants.Status;
import api.routes.Routes;
import api.services.ProductService;
import api.utils.Expected;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class RemoveFromWishlist {
ProductService productService;
        @BeforeMethod
        public void setProductService(){
    this.productService = new ProductService();
        }
    @Test
    public void removeFromWishlistASAnAdmin(){
        String productId = productService.getProductIdToRemove(LoginAs.ADMIN);
        Response response = productService.removeFromWishlist(productId, LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.PRODUCT_REMOVED);
        Response wishlistResponse = productService.getWishList(LoginAs.ADMIN);
        List<String> wishList = wishlistResponse.jsonPath().getList("data.productId");
        Assert.assertFalse(wishList.contains(productId));
    }

    @Test
    public void removeFromWishlistCustomer(){
        String productId = productService.getProductIdToRemove(LoginAs.CUSTOMER);
        Response response = productService.removeFromWishlist(productId, LoginAs.CUSTOMER);

        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.PRODUCT_REMOVED);
        Response wishlistResponse = productService.getWishList(LoginAs.CUSTOMER);
        List<String> wishList = wishlistResponse.jsonPath().getList("data.productId");
        Assert.assertFalse(wishList.contains(productId));
    }
    @Test
    public void removeFromWishlistWithoutLogin(){
        String productId = productService.getProductIdToRemove(LoginAs.CUSTOMER);
        Response response = productService.removeFromWishlist(productId, LoginAs.NONE);

        Assert.assertEquals(response.statusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
        Response wishlistResponse = productService.getWishList(LoginAs.CUSTOMER);
        List<String> wishList = wishlistResponse.jsonPath().getList("data.productId");
        Assert.assertTrue(wishList.contains(productId));
    }

    @Test
    public void removeUnexistingId(){
        String productId = productService.getProductIdToRemove(LoginAs.CUSTOMER);
        productService.removeFromWishlist(productId, LoginAs.CUSTOMER);
        Response response = productService.removeFromWishlist(productId, LoginAs.CUSTOMER);
        Assert.assertEquals(response.statusCode(), Status.NOT_FOUND);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertTrue(response.jsonPath().getString("message").contains(Expected.NOT_FOUND));
        Assert.assertNull(response.jsonPath().get("data"));
        }
    }


