package apitests.reviews;

import api.constants.Status;
import api.services.ProductService;
import api.utils.Faker;
import api.utils.LoginAs;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetProductReviews {
    private ProductService productService;
    private String validProductId;

    @BeforeMethod
    public void setProductService() {
        this.productService = new ProductService();
        this.validProductId = productService.getProductParams(LoginAs.ADMIN).get("productId").toString();
    }

    @Test
    public void getReviewsForSpecificPageSuccess() {
        int page = 50;
        String sort = "newest";

        Response response = productService.getProductReviews(validProductId, page, sort, LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

        Assert.assertEquals(response.jsonPath().getInt("pagination.page"), page);
    }

    @Test
    public void getReviewsWithAlternativeSortSuccess() {
        int page = 1;
        String sort = "oldest";

        Response response = productService.getProductReviews(validProductId, page, sort, LoginAs.NONE);

        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

        Assert.assertEquals(response.jsonPath().getInt("pagination.page"), page);
    }
    @Test
    public void getReviewsWithoutLogin() {
        int page = 1;
        String sort = "oldest";

        Response response = productService.getProductReviews(validProductId, page, sort, LoginAs.NONE);

        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

        Assert.assertEquals(response.jsonPath().getInt("pagination.page"), page);
    }
    @Test
    public void getReviewsWithInvalidProductId() {
        String invalidProductId = Faker.getRandomId();
        int page = 1;
        Response response = productService.getProductReviews(invalidProductId, page, "newest", LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.NOT_FOUND, Message.NON_EXISTING_ID_CANT_BE_GOTTEN);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Product not found");
    }

    @Test
    public void getReviewsWithNegativePageNumber() {
        int invalidPage = -5;
        Response response = productService.getProductReviews(validProductId, invalidPage, "newest", LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST, Message.FAILED_TO_REJECT_NEGATIVE_PAGE);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertNotEquals(response.jsonPath().getString("message"), "Success");
    }

    @Test
    public void getReviewsWithInvalidSortParameter() {
        int page = 1;
        String invalidSort = "invalid_sort_value";
        Response response = productService.getProductReviews(validProductId, page, invalidSort, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST, Message.FAILED_TO_REJECT_INVALID_SORT);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertNotEquals(response.jsonPath().getString("message"), "Success");
    }
}