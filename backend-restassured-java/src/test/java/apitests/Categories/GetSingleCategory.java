package apitests.Categories;

import api.services.ProductService;
import api.utils.Config;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSingleCategory {
    @Test
    public void getSingleCategory(){
        Response response1 = new ProductService().getProductCategories();
        String parentId = response1.jsonPath().getString("data.find { it.name == 'Electronics' }.id");
        String slug = new ProductService().createCategory(parentId).jsonPath().getString("data.slug");
        Response response = new ProductService().getSingleCategory(slug, LoginAs.ADMIN);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertNotNull(response.jsonPath().getString("data.id"));
        Assert.assertEquals(response.jsonPath().getString("data.slug"), slug);
        Assert.assertEquals(response.jsonPath().get("data.parentId"), parentId);
    }
}
