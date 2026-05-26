package flows;

import managers.PageManager;
import pages.CheckoutPage;

public class CheckoutFlow {
    AddToCartFlow addToCartFlow;


    public CheckoutFlow(PageManager pages){
        this.addToCartFlow = new AddToCartFlow(pages);

    }

    public CheckoutPage goToCheckout(){

        return addToCartFlow.addToCartFromProductPage().goToCheckout();
    }

    public CheckoutPage placeOderWithNoAddress(){
        return goToCheckout().clickContinueToPayment().selectMobileMoney().clickReviewOrder()
                .clickPlaceOrder();
    }

    public CheckoutPage placeOrderWithAddress(){
    return goToCheckout()
            .addNewAddress()
            .clickContinueToPayment()
            .enterOrderNotes().clickReviewOrder()
            .clickPlaceOrder();
    }

}