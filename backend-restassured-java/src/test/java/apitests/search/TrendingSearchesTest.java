package apitests.search;

import api.base.BaseAPI;
import api.constants.Status;
import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class TrendingSearchesTest extends BaseAPI {
    ProductService productService;

    @BeforeMethod
    public void setUp() {
        this.productService = new ProductService();
    }

    @Test(description = "Verify that trending searches return a populated list of top search keywords")
    public void getTrendingSearchesSuccess() {
        Response response = productService.getTrendingSearches(LoginAs.CUSTOMER);

        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));

        List<Map<String, Object>> trendingItems = response.jsonPath().getList("data");
        Assert.assertNotNull(trendingItems);
        Assert.assertFalse(trendingItems.isEmpty());
    }


    @Test(description = "Verify that trending search listings follow a realistic result limit")
    public void verifyTrendingListBoundaries() {
        Response response = productService.getTrendingSearches(LoginAs.CUSTOMER);
        Assert.assertEquals(response.getStatusCode(), Status.OK);

        List<Map<String, Object>> trendingItems = response.jsonPath().getList("data");

        int maxAllowedTrendingTerms = 20;
        Assert.assertTrue(trendingItems.size() <= maxAllowedTrendingTerms);
    }

    @Test(description = "Verify consistency and stability of trending terms results across consecutive executions")
    public void verifyTrendingResultsConsistency() {
        Response firstCall = productService.getTrendingSearches(LoginAs.CUSTOMER);
        Response secondCall = productService.getTrendingSearches(LoginAs.CUSTOMER);

        Assert.assertEquals(firstCall.getStatusCode(), Status.OK);
        Assert.assertEquals(secondCall.getStatusCode(), Status.OK);

        List<String> firstKeywords = firstCall.jsonPath().getList("data.keyword");
        List<String> secondKeywords = secondCall.jsonPath().getList("data.keyword");

        Assert.assertEquals(firstKeywords, secondKeywords);
    }

    @Test(description = "Verify public access or default authorization fallback for guest users")
    public void getTrendingSearchesAsGuest() {
        Response response = productService.getTrendingSearches(null);
        Assert.assertEquals(response.getStatusCode(), Status.OK);
    }
}