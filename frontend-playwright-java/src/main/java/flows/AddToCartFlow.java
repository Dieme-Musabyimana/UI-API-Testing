package flows;

import com.microsoft.playwright.Page;
import managers.PageManager;
import pages.HomePage;
import pages.ProductPage;

public class AddToCartFlow {
    private final PageManager pages;
    flows.ProductDetailsFlow flow;
//    Page page = this.pages.getPage();

    public AddToCartFlow(PageManager pages){
        this.pages = pages;
        flow = new flows.ProductDetailsFlow(pages);
    }

    public ProductPage addToCartFromProductPage(){
        flow.goToProductPage().addToCart();
        return pages.getProductPage();
    }

    public HomePage addToCartWithoutLogin(){
        HomePage homePage = pages.getHomePage();
      return   homePage.addToCartWithoutLogin();

    }
}
