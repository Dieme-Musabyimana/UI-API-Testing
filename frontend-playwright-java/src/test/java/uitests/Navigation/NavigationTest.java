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
      flow.login().navigateProductPage();
      Assert.assertTrue(page.url().contains("product"));
      Assert.assertEquals(page.title(), "All Products");
  }

  @Test
    public void flasPageNavigationtest(){
        flow.login().navigatToFlashPage();

  }

  @Test
    public void featurePageNavigationTest(){
        flow.login().navigateToFeature();
  }

  @Test
    public void wishListNavigationTest(){
        flow.login().navigateToWishList();
  }

}
