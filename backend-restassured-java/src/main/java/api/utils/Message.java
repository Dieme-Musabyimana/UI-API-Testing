package api.utils;

public class Message {

    public static final String GUEST_CART_ADDITION_BLOCKED =
            "Guest users (not logged in) must be able to add items to the cart.";

    public static final String FAILED_TO_REJECT_BLANK_ID =
            "The system must reject cart removal requests containing blank or white-space item IDs.";

    public static final String FAILED_TO_REJECT_COD_PROOF =
            "Payment proof upload must be rejected for CASH_ON_DELIVERY orders, as payment is handled in cash upon delivery.";

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
}
