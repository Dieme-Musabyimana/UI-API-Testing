package apitests.Categories;

import api.constants.Status;
import api.services.ProductService;
import api.utils.Expected;
import api.utils.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCategory {
    @Test
  public void createCategory(){
        Response response1 = new ProductService().getProductCategories();
        String parentId = response1.jsonPath().getString("data.find { it.name == 'Electronics' }.id");
        Response response = new ProductService().createCategory(parentId);
        Assert.assertEquals(response.statusCode(), Status.CREATED);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Category created");
  }

  @Test
    public void createCategoryWithWrongParentId(){
        String parentId = Faker.getRandomId();
        Response response = new ProductService().createCategory(parentId);
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST);
      Assert.assertFalse(response.jsonPath().getBoolean("success"));
      Assert.assertTrue(response.jsonPath().getString("message").contains(Expected.NOT_FOUND));
  }

}
