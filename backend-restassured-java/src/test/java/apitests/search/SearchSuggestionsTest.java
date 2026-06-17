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

public class SearchSuggestionsTest extends BaseAPI {
    ProductService productService;

    @BeforeMethod
    public void setUp() {
        this.productService = new ProductService();
    }

    @Test(description = "Verify successful suggestions retrieval with a valid partial query")
    public void getSuggestionsWithValidQuery() {
        Response response = productService.getSearchSuggestions("jea", LoginAs.CUSTOMER);
        Assert.assertEquals(response.getStatusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));

        List<String> productNames = response.jsonPath().getList("data.name");
        Assert.assertNotNull(productNames);
        Assert.assertFalse(productNames.isEmpty());

        for (String name : productNames) {
            Assert.assertTrue(name.toLowerCase().contains("jea"));
        }
    }

    @Test(description = "Verify search suggestions are case-insensitive")
    public void getSuggestionsCaseInsensitivity() {
        Response response = productService.getSearchSuggestions("JEA", LoginAs.CUSTOMER);
        Assert.assertEquals(response.getStatusCode(), Status.OK);

        List<String> productNames = response.jsonPath().getList("data.name");
        Assert.assertFalse(productNames.isEmpty());
        for (String name : productNames) {
            Assert.assertTrue(name.toLowerCase().contains("jea"));
        }
    }

    @Test(description = "Verify validation constraint or empty array when query is empty")
    public void getSuggestionsWithEmptyQuery() {
        Response response = productService.getSearchSuggestions("", LoginAs.CUSTOMER);
        if (response.getStatusCode() == 200) {
            List<String> productNames = response.jsonPath().getList("data.name");
            Assert.assertTrue(productNames.isEmpty());
        } else {
            Assert.assertEquals(response.getStatusCode(), Status.BAD_REQUEST);
        }
    }

    @Test(description = "Verify handling of special characters in the suggestion query")
    public void getSuggestionsWithSpecialCharacters() {
        Response response = productService.getSearchSuggestions("jeans!@#", LoginAs.CUSTOMER);
        Assert.assertEquals(response.getStatusCode(), Status.OK);

        List<String> productNames = response.jsonPath().getList("data.name");
        Assert.assertTrue(productNames.isEmpty());
    }

    @Test(description = "Verify behavior when no products match the query string")
    public void getSuggestionsWithNoMatchingProducts() {
        Response response = productService.getSearchSuggestions("xyznonexistentqueryabc", LoginAs.CUSTOMER);
        Assert.assertEquals(response.getStatusCode(), Status.OK);

        List<String> productNames = response.jsonPath().getList("data.name");
        Assert.assertTrue(productNames.isEmpty());
    }

    @Test(description = "Verify boundary limits on extremely long query lengths")
    public void getSuggestionsWithExcessiveQueryLength() {
        String longQuery = "a".repeat(500);
        Response response = productService.getSearchSuggestions(longQuery, LoginAs.CUSTOMER);
        List<String> productNames = response.jsonPath().getList("data.name");
        Assert.assertTrue(productNames.isEmpty());
        Assert.assertTrue(response.getStatusCode() == Status.OK ||
                response.getStatusCode() == Status.BAD_REQUEST);
    }
}