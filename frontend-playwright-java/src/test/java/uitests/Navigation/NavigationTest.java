package uitests.Navigation;

import base.BaseTest;
import flows.AuthFlow;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class NavigationTest extends BaseTest {
    AuthFlow flow;

    @BeforeMethod
    public void setFlow(){
        this.flow = new AuthFlow(pages);
    }

  @Test
    public void productNavigationTest(){
      flow.login().navigateShopPage();
      Assert.assertTrue(page.url().contains("product"));
      Assert.assertEquals(pages.getShopPage().getProductPageHeading(),"All Products");
  }

  @Test
    public void flashPageNavigationTest(){
        flow.login().navigatToFlashPage();
        Assert.assertTrue(page.url().contains("flash"));
        Assert.assertTrue(pages.getFlashPage().getFlashPageHeading().contains("Flash Sales"));

  }

  @Test
    public void featurePageNavigationTest(){
        flow.login().navigateToFeature();
        Assert.assertTrue(page.url().contains("feature"));
        Assert.assertEquals(pages.getFeaturePage().getFeaturePageHeading(), "⭐ Featured");
  }

  @Test
    public void wishListNavigationTest(){
        flow.login().navigateToWishList();
        Assert.assertTrue(page.url().contains("wishlist"));
        Assert.assertTrue(pages.getWishListPage().getWishListHeading().contains("Wishlist"));
  }

}
