package reviews;

import api.services.ProductService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubmitReview {
    ProductService productService;
    @BeforeMethod
    public void setProductService(){
        this.productService = new ProductService();
    }

    @Test
    public void submitRivew(){

    }
}
