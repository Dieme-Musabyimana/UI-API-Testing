package uitests.Cart;

import base.BaseTest;
import constants.Assertions;
import flows.AddToCartFlow;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

public class AddToCartTests extends BaseTest {
    AddToCartFlow flow;
    HomePage homePage;

    @BeforeMethod
    public void AddToCartTests(){
        this.flow = new AddToCartFlow(pages);
        this.homePage = pages.getHomePage();
    }

    @Test
    public void addToCartTest(){
        flow.addToCartFromProductPage();
        Assert.assertTrue(locators.vewCartBtn.isEnabled());
        Assert.assertTrue(locators.checkoutBtn.isEnabled());
    }

    @Test
    public void addToCartWithoutLogin(){
        flow.addToCartWithoutLogin();
        Assert.assertEquals(homePage.getAddToCartSuccessMessage(), Assertions.addToCartSuccessMessage);
    }

}
