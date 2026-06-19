package apitests.wishlist;

import api.constants.Status;
import api.services.ProductService;
import api.utils.Expected;
import api.utils.LoginAs;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MoveWishlistToCart {
    ProductService productService;

    @BeforeMethod
    public void setProductService(){
        this.productService = new ProductService();
    }

    @Test(description = "BUG-1042: Move to cart throws 500 error on variantId")
       public void moveToCartAsCustomer(){
        String productId = productService.getProductParams(LoginAs.ADMIN).get("productId").toString();
        productService.addProductToWishlist(productId, LoginAs.ADMIN);

        Response response = productService.moveWishlistToCart(productId, LoginAs.ADMIN);
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.MOVED_TO_CART, Message.WISHLIST_MOVE_TO_CART_FAILED);
        Assert.assertEquals(response.statusCode(), Status.OK, Message.WISHLIST_MOVE_TO_CART_FAILED);
    }
}
