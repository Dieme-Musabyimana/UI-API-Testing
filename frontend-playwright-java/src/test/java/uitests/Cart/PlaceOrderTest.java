package uitests.Cart;

import base.BaseTest;
import constants.Assertions;
import flows.CheckoutFlow;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutPage;

public class PlaceOrderTest extends BaseTest {
    CheckoutFlow checkoutFlow;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setCheckoutFlow(){
        this.checkoutFlow = new CheckoutFlow(pages);
        this.checkoutPage = new CheckoutPage(page);
    }

    @Test

    public void placeOrderWithExistingAddress(){
        checkoutFlow.placeOderWithNoNewAddress();
        Assert.assertTrue(checkoutPage.getOrderSuccessMesage().contains(Assertions.ORDER_SUCCESS_MESSAGE));

    }

    @Test
    public void placeOrderWithNewAddress(){
        checkoutFlow.placeOrderWithNewAddress();
        Assert.assertTrue(checkoutPage.getOrderSuccessMesage().contains(Assertions.ORDER_SUCCESS_MESSAGE));

    }
}
