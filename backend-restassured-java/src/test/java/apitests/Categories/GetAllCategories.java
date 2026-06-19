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
    }
}
