package apitests.CartAPI;

import api.constants.StatusCodes;
import api.payloads.RequestPayloads;
import api.services.CartService;
import api.services.ProductService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.geom.RectangularShape;

public class AddProductToCart {

    @Test
    public void addProductToCart(){
        Response response = new CartService().addToCart();
        Assert.assertEquals(response.statusCode(), StatusCodes.OK);
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.ADDED);
    }
}
