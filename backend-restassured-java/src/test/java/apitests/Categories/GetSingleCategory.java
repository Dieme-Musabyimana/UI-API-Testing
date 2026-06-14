package apitests.Categories;

import api.services.ProductService;
import api.utils.Config;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSingleCategory {
    @Test
    public void getSingleCategory(){
        String slug = Config.getCategorySlug();
        Response response = new ProductService().getSingleCategory(slug, true);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertNotNull(response.jsonPath().getString("data.id"));
        Assert.assertEquals(response.jsonPath().getString("data.slug"), "bags-luggage");
        Assert.assertEquals(response.jsonPath().getString("data.name"), "Bags & Luggage");
        Assert.assertNull(response.jsonPath().get("data.image"));
        Assert.assertNull(response.jsonPath().get("data.parentId"));
        Assert.assertNotNull(response.jsonPath().getList("data.children"));
        Assert.assertEquals(response.jsonPath().getInt("data._count.products"), 24);
    }
}
