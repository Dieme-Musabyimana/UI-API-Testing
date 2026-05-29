package pages;

import com.microsoft.playwright.Page;
import constants.Locators;

public class ProductPage {
    private final Locators locators;
    private final Page page;

    public ProductPage(Page page) {
        this.locators = new Locators(page);
        this.page  = page;
    }

    public ProductPage addToCart(){

        try {
            locators.size.first().waitFor(new com.microsoft.playwright.Locator.WaitForOptions().setTimeout(2000));
            locators.size.first().click();
        } catch (Exception e) {
            System.out.println("No size option found or required for this product.");
        }
        try {
            locators.color.first().waitFor(new com.microsoft.playwright.Locator.WaitForOptions().setTimeout(2000));
            locators.color.first().click();
        } catch (Exception e) {
            System.out.println("No color option found or required for this product.");
        }
        locators.addToCartBtn.click();
        locators.addToCartPopUP.waitFor();
        return new ProductPage(page);
    }

    public CheckoutPage goToCheckout(){
        locators.checkoutBtn.click();
        return new CheckoutPage(page);
    }
}
