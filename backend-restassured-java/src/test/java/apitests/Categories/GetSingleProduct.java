package apitests.Categories;

import api.services.ProductService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSingleProduct {

    @Test
    public void getSingleCategory(){
        Response response = new ProductService().getSingleCategory();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertEquals(response.jsonPath().getString("data.id"), "24517e2b-3a02-4bfa-aca1-6a9198dc8c70");
        Assert.assertEquals(response.jsonPath().getString("data.slug"), "bags-luggage");
        Assert.assertEquals(response.jsonPath().getString("data.name"), "Bags & Luggage");
        Assert.assertNull(response.jsonPath().get("data.image"));
        Assert.assertNull(response.jsonPath().get("data.parentId"));
        Assert.assertNotNull(response.jsonPath().getList("data.children"));
        Assert.assertEquals(response.jsonPath().getInt("data._count.products"), 24);
    }
}
