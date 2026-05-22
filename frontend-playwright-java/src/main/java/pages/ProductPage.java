package pages;

import com.microsoft.playwright.Page;
import constants.Locators;

public class ProductPage {
    private final Locators locators;

    public ProductPage(Page page) {
        this.locators = new Locators(page);
    }

    public void addToCart(){
        if (locators.size.first().isVisible()) {
            locators.size.first().click();
        }
        if (locators.color.first().isVisible()) {
            locators.color.first().click();
        }
        locators.addToCartBtn.click();    }
}
