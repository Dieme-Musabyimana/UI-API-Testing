package apitests.Product;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetRelatedProducts extends BaseAPI {

    @Test
    public void getRelatedProducts(){
        Response response = new ProductService().getRelatedProducts(LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");
        List<String> allProducts = response.jsonPath().getList("data");
        Assert.assertFalse(allProducts.isEmpty());
        String expectedCategoryId = response.jsonPath().getString("data[0].categoryId");
        for(int i = 0; i < allProducts.size(); i++){
            Assert.assertEquals(response.jsonPath().getString("data["+ i +"].categoryId"), expectedCategoryId);
            Assert.assertTrue(response.jsonPath().getBoolean("data["+ i +"].isActive"));
        }

        }
    }


