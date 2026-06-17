package apitests.search.fullTextWithFilters;

import api.services.ProductService;
import api.utils.LoginAs;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchWithAllCombinedQueries {
    private ProductService productService;

    @BeforeMethod
    public void setProductService(){
        this.productService = new ProductService();
    }

    @Test(description = "Verify search combined with all optional filters and valid sorting layout strategy")
    public void testSearchWithAllFiltersActive() {
        String queryKeyword = "jeans";
        Integer page = 1;
        Integer limitValue = 10;
        String targetCategory = "womens-fashion";
        Integer minPrice = 5;
        Integer maxPrice = 50;
        String sortOrder = "price_asc";

        Response response = productService.fullTextSearch(
                queryKeyword, page, limitValue,
                targetCategory, minPrice, maxPrice,
                sortOrder, LoginAs.ADMIN
        );

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));

        Assert.assertEquals(response.jsonPath().getInt("pagination.page"), page);
        Assert.assertEquals(response.jsonPath().getInt("pagination.limit"), limitValue);

        List<String> names = response.jsonPath().getList("data.name");
        List<String> categorySlugs = response.jsonPath().getList("data.category.slug");
        List<Float> prices = response.jsonPath().getList("data.price");

        Assert.assertFalse(names.isEmpty());
        Assert.assertTrue(names.size() <= limitValue);

        for (int i = 0; i < names.size(); i++) {
            String currentName = names.get(i);
            String currentSlug = categorySlugs.get(i);
            Float currentPrice = prices.get(i);

            Assert.assertTrue(currentName.toLowerCase().contains(queryKeyword));
            Assert.assertEquals(currentSlug, targetCategory);
            Assert.assertTrue(currentPrice >= minPrice);
            Assert.assertTrue(currentPrice <= maxPrice);
        }

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1));
        }
    }

    @Test(description = "Verify that searching with all missing queries is rejected")
    public void verifySearchFailsWhenMandatoryKeyWord() {
        Response response = productService.fullTextSearch(
                null, 2,
                null, "womens-fashion",
                5, 20,
                null, LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), Message.FAILED_TO_REJECT_WHEN_MANDATORY_QUERY_IS_MISSING);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));

    }
    @Test(description = "Verify that searching with all missing queries is rejected")
    public void verifySearchFailsWhenKeywordsAreMissing() {
        Response response = productService.fullTextSearch(
                null, null,
                null, null,
                null, null,
                null, LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), Message.FAILED_TO_REJECT_ALL_MISSING_QUERIES);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));

    }
}
