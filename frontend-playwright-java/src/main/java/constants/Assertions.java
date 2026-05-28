package constants;

public class Assertions {
    private final Locators locators;
    public static final String emptyFldErrMsg = "Please fill out this field.";
    public static final String expProductQnty = "$17.99";
    public static final String ORDER_SUCCESS_MESSAGE = "Order placed successfully";

    public Assertions(Locators locators) {
        this.locators = locators;
    }

}