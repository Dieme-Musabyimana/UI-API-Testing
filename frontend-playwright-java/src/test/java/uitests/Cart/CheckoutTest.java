package uitests.Cart;

import api.services.AuthService;
import base.BaseTest;
import constants.Assertions;
import flows.CheckoutFlow;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    CheckoutFlow checkoutFlow;

    @BeforeMethod
    public void CheckoutFlow(){
        this.checkoutFlow = new CheckoutFlow(pages);
    }


}