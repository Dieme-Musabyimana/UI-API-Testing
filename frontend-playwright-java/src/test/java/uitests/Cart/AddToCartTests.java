package uitests.Cart;

import base.BaseTest;
import flows.AddToCartFlow;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCartTests extends BaseTest {
    AddToCartFlow flow;

    @BeforeMethod
    public void AddToCartTests(){
        this.flow = new AddToCartFlow(pages);
    }

    @Test
    public void addToCartTest(){
        flow.addToCartFromProductPage();
        Assert.assertTrue(locators.vewCartBtn.isEnabled());
        Assert.assertTrue(locators.checkoutBtn.isEnabled());
    }

}
