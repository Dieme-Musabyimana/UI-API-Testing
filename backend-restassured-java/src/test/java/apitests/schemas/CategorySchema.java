package apitests.schemas;

import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CategorySchema {

    @Test
    public void validateCategorySchema(){
        Response response1 = new ProductService().getProductCategories();
        String parentId = response1.jsonPath().getString("data.find { it.name == 'Electronics' }.id");
        String slug = new ProductService().createCategory(parentId).jsonPath().getString("data.slug");
        Response response = new ProductService().getSingleCategory(slug, LoginAs.ADMIN);
        SchemaValidation.validate(response, "categorySchema", "data", null);
    }
}
