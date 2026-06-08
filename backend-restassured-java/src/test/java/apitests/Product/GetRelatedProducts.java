package apitests.Product;

import api.base.BaseAPI;
import api.constants.StatusCodes;
import api.services.ProductService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetRelatedProducts extends BaseAPI {

    @Test

    public void getRelatedProducts(){
        Response response = new ProductService().getRelatedProducts();


        Assert.assertEquals(response.statusCode(), StatusCodes.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

        List<String> allProducts = response.jsonPath().getList("data");
        String ExpectedCategoryId = response.jsonPath().getString("data[0].categoryId");
        for(int i = 0; i<allProducts.size(); i++){
            Assert.assertEquals(response.jsonPath().getString("data["+ i +"].categoryId"), ExpectedCategoryId);
            Assert.assertTrue(response.jsonPath().getBoolean("data["+ i +"].isActive"));
        }

        }
    }


