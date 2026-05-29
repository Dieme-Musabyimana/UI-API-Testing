package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
public class ShopPage {
    private Page page;
    private final Locator productPageHeading;

    public ShopPage(Page page){
        this.page = page;
        this.productPageHeading = page.locator("h1[class='font-display font-bold text-2xl md:text-3xl text-white']");
    }

    public String getProductPageHeading(){
        return productPageHeading.innerText();
    }
}