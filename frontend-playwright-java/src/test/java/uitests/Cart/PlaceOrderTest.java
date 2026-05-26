package uitests.Cart;

import base.BaseTest;
import constants.Assertions;
import flows.CheckoutFlow;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {
    CheckoutFlow checkoutFlow;

    @BeforeMethod
    public void setCheckoutFlow(){
        this.checkoutFlow = new CheckoutFlow(pages);
    }

    @Test

    public void placeOrderWithNoAddress(){
        checkoutFlow.placeOderWithNoAddress();
        System.out.println("<<<<<<<<<<<" + (page.locator(Assertions.ORDER_SUCCESS_MESSAGE).textContent()));

        Assert.assertTrue(page.locator(Assertions.ORDER_SUCCESS_MESSAGE).first().isVisible(), "Order success message should be visible");

    }

    @Test
    public void placeOrderWithAddress(){
        checkoutFlow.placeOrderWithAddress();
    }
}
