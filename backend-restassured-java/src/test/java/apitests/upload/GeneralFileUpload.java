package apitests.upload;

import api.services.ProductService;
import org.testng.annotations.BeforeMethod;

public class GeneralFileUpload {
    ProductService productService;

    @BeforeMethod
    public void setUp() {
        this.productService = new ProductService();
    }
}
