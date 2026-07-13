package apitests.search.fullTextWithFilters;

import api.services.ProductService;
import api.utils.LoginAs;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Sorting {
    private ProductService productService;

    @BeforeMethod
    public void setProductService(){
        this.productService = new ProductService();
    }

    @Test(description = "Verify parameter 'sort' coordinates value ordering alignment correctly for price_asc")
    public void sortResultsInAscendingOrderOfPrice() {
        Response response = productService.fullTextSearch(
                "jeans", null,
                null, null,
                null, null,
                "price_asc", LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);

        List<Number> prices = response.jsonPath().getList("data.price");
        Assert.assertFalse(prices.isEmpty());

        for (int i = 0; i < prices.size() - 1; i++) {
            float current = prices.get(i).floatValue();
            float next = prices.get(i + 1).floatValue();

            Assert.assertTrue(current <= next, Message.PRICE_SORT_ASCENDING_MISMATCH);
        }
    }

    @Test(description = "Verify parameter 'sort' coordinates value ordering alignment correctly for price_desc")
    public void sortResultsInDescendingOrderOfPrice() {
        Response response = productService.fullTextSearch(
                "jeans", null,
                null, null,
                null, null,
                "price_desc", LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);

        List<Float> prices = response.jsonPath().getList("data.price");
        Assert.assertFalse(prices.isEmpty());

        for (int i = 0; i < prices.size() - 1; i++) {
            float current = prices.get(i);
            float next = prices.get(i + 1);

            Assert.assertTrue(current >= next);
        }
    }

    @Test(description = "Verify parameter 'sort' coordinates value ordering alignment correctly for rating")
    public void sortResultsFromHighestToLowestRating() {
        Response response = productService.fullTextSearch(
                "jeans", null,
                null, null,
                null, null,
                "rating", LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);

        List<?> ratingsRaw = response.jsonPath().getList("data.rating");
        Assert.assertFalse(ratingsRaw.isEmpty());

        for (int i = 0; i < ratingsRaw.size() - 1; i++) {
            float current = Float.parseFloat(ratingsRaw.get(i).toString());
            float next = Float.parseFloat(ratingsRaw.get(i + 1).toString());

            Assert.assertTrue(current >= next);
        }
    }

    @Test(description = "Verify parameter 'sort' coordinates value ordering alignment correctly for newest")
    public void sortResultsFromNewestToOldestCreationDate() {
        Response response = productService.fullTextSearch(
                "jeans", null,
                null, null,
                null, null,
                "newest", LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);
        List<String> creationDates = response.jsonPath().getList("data.createdAt");
        Assert.assertFalse(creationDates.isEmpty());

        for (int i = 0; i < creationDates.size() - 1; i++) {
            java.time.Instant currentItemTime = java.time.Instant.parse(creationDates.get(i));
            java.time.Instant nextItemTime = java.time.Instant.parse(creationDates.get(i + 1));
            Assert.assertTrue(!currentItemTime.isBefore(nextItemTime));
        }
    }

    @Test(description = "Verify parameter 'sort' accepts relevance and responds successfully")
    public void verifySuccessfulResponseWhenSortingByRelevance() {
        Response response = productService.fullTextSearch(
                "jeans", null,
                null, null,
                null, null,
                "relevance", LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), 200);
        List<Object> data = response.jsonPath().getList("data");
        Assert.assertNotNull(data);
        Assert.assertFalse(data.isEmpty());
    }
}