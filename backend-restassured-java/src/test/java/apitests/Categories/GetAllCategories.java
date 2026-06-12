package apitests.Categories;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.ProductService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllCategories extends BaseAPI {

    @Test
    public void getAllCategoriesTest(){
      Response response =  new ProductService().getProductCategories();

        Assert.assertEquals(response.getStatusCode(), Status.OK);

        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        Assert.assertNotNull(response.jsonPath().getList("data"));
        Assert.assertFalse(response.jsonPath().getList("data").isEmpty());
        Assert.assertEquals(response.jsonPath().getString("data[0].id"), "24517e2b-3a02-4bfa-aca1-6a9198dc8c70");
        Assert.assertEquals(response.jsonPath().getString("data[0].name"), "Bags & Luggage");
        Assert.assertEquals(response.jsonPath().getString("data[0].slug"), "bags-luggage");
    }
}
