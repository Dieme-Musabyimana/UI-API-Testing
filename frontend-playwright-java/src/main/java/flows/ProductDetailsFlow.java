package flows;

import managers.PageManager;
import pages.ProductPage;

public class ProductDetailsFlow {
    AuthFlow flow;

    public ProductDetailsFlow(PageManager pages){
        this.flow = new AuthFlow(pages);
    }
    public ProductPage goToProductPage(){
       return flow.login().goToSingleProductPage();
    }
}
