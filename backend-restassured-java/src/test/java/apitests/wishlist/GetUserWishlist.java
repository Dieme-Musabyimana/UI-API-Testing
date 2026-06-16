package apitests.wishlist;

import api.constants.Status;
import api.services.ProductService;
import api.utils.Expected;
import api.utils.LoginAs;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserWishlist {

    @Test
    public void getWishListWithLoginAsAnAdmin(){
        Response response = new ProductService().getWishList(LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertNotNull(response.jsonPath().getList("data"));
    }

    @Test
    public void getWishListWithLoginAsCUSTOMER(){
        Response response = new ProductService().getWishList(LoginAs.CUSTOMER);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertNotNull(response.jsonPath().getList("data"));
    }
    @Test
    public void getWishListWithoutLogin(){
        Response response = new ProductService().getWishList(LoginAs.NONE);
        Assert.assertEquals(response.statusCode(), Status.UNAUTHORIZED);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.AUTHENTICATION_ERROR);
        Assert.assertNull(response.jsonPath().get("data"));
    }
}
