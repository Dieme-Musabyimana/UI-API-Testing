package apitests.Categories;

import api.services.ProductService;
import org.testng.annotations.Test;

public class CreateCategory {

  @Test

    public void creatCategory(){
      new ProductService().createCategory();
  }
}
