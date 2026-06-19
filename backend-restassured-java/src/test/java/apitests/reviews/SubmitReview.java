package apitests.reviews;

import api.constants.Status;
import api.services.ProductService;
import api.utils.Expected;
import api.utils.Faker;
import api.utils.LoginAs;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubmitReview {
    private ProductService productService;
    private String validProductId;

    @BeforeMethod
    public void setProductService() {
        this.productService = new ProductService();
        this.validProductId = productService.getProductParams(LoginAs.ADMIN).get("productId").toString();
    }

    @Test(description = "Verify an authenticated user can successfully submit a review with valid data")
    public void submitValidReview() {
        int rating = 5;
        String title = "Excellent Quality";
        String description = "The high-density nylon material is durable. Strongly recommended!";
        Response response = productService.submitReview(rating, title, description, validProductId, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.CREATED, Message.FAILED_TO_SUBMIT_VALID_REVIEW);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.SUBMITTED);
    }


    @Test(description = "Verify system rejects review ratings that exceed the maximum boundary (e.g., > 5)")
    public void submitReviewWithOutOfBoundsRating() {
        int invalidHighRating = 8;
        String title = Faker.getFirstName();
        String description = Faker.getFirstName();
        Response response = productService.submitReview(invalidHighRating, title, description, validProductId, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST, Message.FAILED_TO_REJECT_INVALID_RATING);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }

    @Test(description = "Verify system rejects review ratings below the minimum boundary (e.g., < 1)")
    public void submitReviewWithNegativeRating() {
        int invalidLowRating = -1;
        String title = Faker.getFirstName();
        String description = Faker.getFirstName();
        Response response = productService.submitReview(invalidLowRating, title, description, validProductId, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST, Message.FAILED_TO_REJECT_INVALID_RATING);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }

    @Test(description = "Verify system rejects review submission with empty/blank required properties")
    public void submitReviewWithMissingRequiredFields() {
        int rating = 4;
        String blankTitle = "";
        String blankDescription = "   ";
        Response response = productService.submitReview(rating, blankTitle, blankDescription, validProductId, LoginAs.ADMIN);
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST, Message.FAILED_TO_REJECT_MISSING_FIELDS);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }

    @Test(description = "Verify system handles review placement requests targeting non-existent products gracefully")
    public void submitReviewToNonExistentProduct() {
        String fakeProductId = "00000000-0000-0000-0000-000000000000";
        int rating = 4;
        String title = Faker.getFirstName();
        String description = Faker.getFirstName();

        Response response = productService.submitReview(rating, title, description, fakeProductId, LoginAs.ADMIN);

        Assert.assertEquals(response.statusCode(), Status.NOT_FOUND, Message.FAILED_TO_REJECT_NON_EXISTENT_PRODUCT);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }
}