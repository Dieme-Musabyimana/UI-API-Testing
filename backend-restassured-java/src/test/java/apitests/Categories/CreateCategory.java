package apitests.Categories;

import api.services.ProductService;
import org.testng.annotations.Test;

public class CreateCategory {
    @Test
  public void createCategory(){
      new ProductService().createCategory();
  }
}
