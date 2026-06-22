package apitests.schemas;

import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ProductSchema {
    @Test

    public void validateProductSchema(){
        ProductService productService = new ProductService();
        String slug = productService.getProductParams(LoginAs.ADMIN).get("slug").toString();
        Response response = productService.getSingleProduct(slug, LoginAs.ADMIN);
        SchemaValidation.validate(response, "productSchema", "data", null);
    }
}
