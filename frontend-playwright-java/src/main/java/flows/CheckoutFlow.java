package flows;

import managers.PageManager;
import pages.CheckoutPage;

public class CheckoutFlow {
    AddToCartFlow addToCartFlow;


    public CheckoutFlow(PageManager pages){
        this.addToCartFlow = new AddToCartFlow(pages);

    }

    public CheckoutPage goToCheckout(){

        return addToCartFlow.addToCartFromProductPage().goToCheckout().clickContinueToPayment().selectMobileMoney().clickReviewOrder();
    }

    public CheckoutPage placeOderWithNoAddress(){
        return goToCheckout().clickPlaceOrder();
    }

    public CheckoutPage placeOrderWithAddress(){

    }

}