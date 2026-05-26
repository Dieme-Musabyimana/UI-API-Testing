package flows;

import managers.PageManager;
import pages.ProductPage;

public class AddToCartFlow {
    private final PageManager pages;
    ProductDetailsFlow flow;

    public AddToCartFlow(PageManager pages){
        this.pages = pages;
        flow = new ProductDetailsFlow(pages);
    }

    public ProductPage addToCartFromProductPage(){
        flow.goToProductPage().addToCart();
        return pages.getProductPage();
    }
}
