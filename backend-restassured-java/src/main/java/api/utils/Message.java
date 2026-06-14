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
}