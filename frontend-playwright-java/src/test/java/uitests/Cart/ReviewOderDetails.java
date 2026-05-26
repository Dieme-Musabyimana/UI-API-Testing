package uitests.Cart;

import api.base.BaseAPI;
import base.BaseTest;
import constants.Data;
import flows.CheckoutFlow;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutPage;

public class ReviewOderDetails extends BaseTest {
    CheckoutFlow checkoutFlow;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void CheckoutFlow(){
        this.checkoutFlow = new CheckoutFlow(pages);
        this.checkoutPage = new CheckoutPage(page);
    }

    @Test
    public void checkoutTest(){
        checkoutFlow.goToCheckout();
        Assert.assertEquals(checkoutPage.getFirstProductName(), Data.productName);
        Assert.assertEquals(checkoutPage.getFirstProductPrice(), "$161.91");
    }
}
