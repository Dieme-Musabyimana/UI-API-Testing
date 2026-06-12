package apitests.Categories;

import api.constants.Status;
import api.services.ProductService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCategory {
    @Test
  public void createCategory(){
        Response response = new ProductService().createCategory();
        Assert.assertEquals(response.statusCode(), Status.CREATED);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Category created");
  }
}
