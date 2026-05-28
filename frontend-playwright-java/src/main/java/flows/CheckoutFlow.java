package flows;

import managers.PageManager;
import pages.CheckoutPage;

public class CheckoutFlow {
    private final AddToCartFlow addToCartFlow;

    public CheckoutFlow(PageManager pages) {
        this.addToCartFlow = new AddToCartFlow(pages);
    }

    public CheckoutPage goToCheckout() {
        return addToCartFlow.addToCartFromProductPage().goToCheckout();
    }

    public CheckoutPage placeOderWithNoNewAddress() {
        return goToCheckout()
                .clickContinueToPayment()
                .selectMobileMoney()
                .clickReviewOrder()
                .clickPlaceOrder();
    }

    public CheckoutPage placeOrderWithNewAddress() {
        return goToCheckout()
                .addNewAddress()
                .clickContinueToPayment()
                .selectMobileMoney()
                .clickReviewOrder()
                .clickPlaceOrder();
    }

}