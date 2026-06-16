package reviews;

import api.constants.Status;
import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetProductReviews {
    ProductService productService;
    @BeforeMethod
    public void setProductService(){
        this.productService = new ProductService();
    }

    @Test
    public void getReviewsForSpecificPage(){
        Response response = productService.getProductReviews(null, 2, null, LoginAs.NONE);
        Assert.assertEquals(response.statusCode(), Status.OK);
    }
}
