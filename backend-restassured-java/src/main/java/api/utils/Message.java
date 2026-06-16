package api.utils;

public class Message {

    public static final String GUEST_CART_ADDITION_BLOCKED =
            "Guest users (not logged in) must be able to add items to the cart.";

    public static final String FAILED_TO_REJECT_NON_EXISTENT_ID =
            "An item with a non-existent ID cannot be successfully removed from the cart.";

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
}
