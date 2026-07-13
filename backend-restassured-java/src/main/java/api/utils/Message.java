package api.utils;

public class Message {

    public static final String GUEST_CART_ADDITION_BLOCKED =
            "Guest users (not logged in) must be able to add items to the cart.";

    public static final String FAILED_TO_REJECT_BLANK_ID =
            "The system must reject cart removal requests containing blank or white-space item IDs.";

    public static final String FAILED_TO_REJECT_COD_PROOF =
            "Payment proof upload should be rejected for CASH_ON_DELIVERY orders, as payment is handled in cash upon delivery.";

    public static final String DATA_TYPE_MISMATCH =
            "Product image upload failed. The request body was passed as a binary file stream (List<File>), " +
                    "but there was a data type mismatch with the API documentation schema, which incorrectly specifies an array of strings (List<String>).";

    public static final String WISHLIST_MOVE_TO_CART_FAILED =
            "Moving an item from the wishlist to the cart failed. Following the API documentation by passing only the product ID path parameter " +
                    "triggered a 500 Internal Server Error because the backend failed to handle the operation without an undocumented variantId body parameter.";

    public static final String FAKE_ITEM_CANT_BE_REMOVED = "The system is showing the successful removal of fake item id or unexisting id";

    public static final String NON_EXISTING_ID_CANT_BE_GOTTEN =
            "The system must return a 404 Not Found error when requesting reviews for a non-existent or fake product ID, instead of returning a 200 OK success payload.";

    public static final String FAILED_TO_REJECT_NEGATIVE_PAGE =
            "The system must reject negative page numbers with a 400 Bad Request error instead of showing a 500 Internal Server Error.";
    public static final String FAILED_TO_REJECT_INVALID_SORT =
            "The system must reject unsupported or invalid sort query parameters with a 400 Bad Request error instead of silently falling back to a default state with a 200 OK.";

    public static final String FAILED_TO_SUBMIT_VALID_REVIEW =
            "An authenticated user should be able to successfully submit a valid review for an existing product.";

    public static final String FAILED_TO_REJECT_INVALID_RATING =
            "The system must reject review ratings outside the acceptable boundaries (e.g., greater than 5 or less than 1) with a 400 Bad Request.";

    public static final String FAILED_TO_REJECT_MISSING_FIELDS =
            "The system must reject review submissions with empty titles or descriptions with a 400 Bad Request.";

    public static final String FAILED_TO_REJECT_NON_EXISTENT_PRODUCT =
            "The system must return a 404 Not Found error when attempting to post a review for a non-existent product ID.";
    public static final String PRICE_SORT_ASCENDING_MISMATCH =
            "The product search list is not properly ordered in ascending sequence by price. Products with higher prices are appearing before lower-priced items.";
    public static final String FAILED_TO_REJECT_ALL_MISSING_QUERIES =
            "API should return a 400 Bad Request status code when all search queries are missing.";
    public static final String FAILED_TO_REJECT_WHEN_MANDATORY_QUERY_IS_MISSING =
            "API should reject the the request which is missing the mandatory query parameter";

    public static final String MISSING_ERROR_FIELD =
            "When sending the negative request, the API response is missing required errors field";
    public static final String FAILS_TO_REJECT_UNREGISTERED_EMAIL =
            "API should reject forgot password request for unregistered emails";
    public static final String FAILS_TO_REJECT_MALFORMED_EMAIL =
            "APi should reject forgot password request for malformed emails";
    public static final String FAILS_TO_REJECT_EMPTY_FIELD_EMAIL =
            "API should reject forgot password request for empy email field";
    public static final String FAILS_TO_REJECT_EXPIRED_REFRESH_TOKEN =
            "API should reject the request with expired refresh token";
    public static final String FAILS_TO_REJECT_MALFORMED_EMAIL_REGISTRATION=
            "API should reject the registration with malformed email";
    public static final String FAILS_TO_REJECT_REGISTRATION_WITH_EMPTY_FIELDS =
            "Registration request with empty password fields should be rejected";
    public static final String FAILS_TO_VERIFY_EMAIL_WITH_VALID_TOKEN =
            "Email should be verified for valid token";
}
