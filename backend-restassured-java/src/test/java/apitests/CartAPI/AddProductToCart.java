package apitests.CartAPI;

import api.constants.StatusCodes;
import api.services.CartService;
import api.services.ProductService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.geom.RectangularShape;

public class AddProductToCart {

    @Test
    public void addProductToCart(){
        Response response = new CartService().addToCart();
        Assert.assertEquals(response.statusCode(), StatusCodes.OK);
    }
}
