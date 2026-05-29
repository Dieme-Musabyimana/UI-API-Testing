package uitests.Cart;

import base.BaseTest;
import flows.CheckoutFlow;
import org.testng.annotations.BeforeMethod;

public class CheckoutTest extends BaseTest {
    CheckoutFlow checkoutFlow;

    @BeforeMethod
    public void CheckoutFlow(){
        this.checkoutFlow = new CheckoutFlow(pages);
    }


}