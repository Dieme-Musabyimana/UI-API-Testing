package apitests.search;

import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SingleQueryWithMandatoryQuerySearch {
    private ProductService productService;

    @BeforeMethod
    public void setProductService(){
        this.productService = new ProductService();
    }

    @Test(description = "Verify parameter 'q' isolates records matching the text keyword")
    public void searchMatchingProductsByKeyword() {
        String searchQuery = "jeans";
        Response response = productService.fullTextSearch(
                searchQuery, null,
                null, null,
                null, null,
                null, LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);
        List<String> names = response.jsonPath().getList("data.name");

        Assert.assertFalse(names.isEmpty());
        for (String name : names) {
            Assert.assertTrue(name.toLowerCase().contains(searchQuery));
        }
    }

    @Test(description = "Verify parameter 'page' returns the correct block of paginated data")
    public void verifyCorrectPageOfPaginatedDataIsReturned() {
        Response response = productService.fullTextSearch(
                "jeans", 1,
                null, null,
                null, null,
                null, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("pagination.page"), 1);
    }

    @Test(description = "Verify parameter 'limit' truncates the data array size precisely")
    public void truncateRecordsToMatchSpecifiedLimit() {
        Integer limitValue = 1;
        Response response = productService.fullTextSearch(
                "jeans", null,
                limitValue, null,
                null, null,
                null, LoginAs.NONE);


        Assert.assertEquals(response.statusCode(), 200);
        List<Object> data = response.jsonPath().getList("data");

        Assert.assertTrue(data.size() <= limitValue, "API returned more records than specified limit allocation");
        Assert.assertEquals(response.jsonPath().getInt("pagination.limit"), limitValue);
    }

    @Test(description = "Verify parameter 'category' scopes products matching that vertical category slug")
    public void filterProductsBySpecificCategory() {
        String targetCategory = "womens-fashion";
        Response response = productService.fullTextSearch(
                "jeans", null,
                null, targetCategory,
                null, null,
                null, LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);
        List<String> categorySlugs = response.jsonPath().getList("data.category.slug");

        Assert.assertFalse(categorySlugs.isEmpty());
        for (String slug : categorySlugs) {
            Assert.assertEquals(slug, targetCategory);
        }
    }

    @Test(description = "Verify parameter 'minPrice' excludes items valued below the minimum limit")
    public void filterAndExcludeItemsBelowMinimumPrice() {
        Integer minPrice = 40;
        Response response = productService.fullTextSearch(
                "jeans", null,
                null, null,
                minPrice, null,
                null, LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);
        List<Float> prices = response.jsonPath().getList("data.price");

        for (Float price : prices) {
            Assert.assertTrue(price >= minPrice);
        }
    }

    @Test(description = "Verify parameter 'maxPrice' excludes items valued above the maximum limit")
    public void filterAndExcludeItemsAboveMaximumPrice() {
        Integer maxPrice = 20;
        Response response = productService.fullTextSearch(
                "jeans", null,
                null, null,
                null, maxPrice,
                null, LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);

        List<Number> prices = response.jsonPath().getList("data.price");

        for (Number price : prices) {
            Assert.assertTrue(price.floatValue() <= maxPrice);
        }
    }

    @Test(description = "Verify parameter 'sort' coordinates value ordering alignment correctly")
    public void sortResultsInAscendingOrderOfPrice() {
        Response response = productService.fullTextSearch(
                "jeans", null,
                null, null,
                null, null,
                "price_asc", LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);
        List<Number> prices = response.jsonPath().getList("data.price");
        for (int i = 0; i < prices.size() - 1; i++) {
            float currentPrice = prices.get(i).floatValue();
            float nextPrice = prices.get(i + 1).floatValue();

            Assert.assertTrue(currentPrice <= nextPrice);
        }
    }
}