package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import managers.PageManager;

public class FeaturePage {
    private PageManager pages;
    private final Locator flashPageHeading;

    public FeaturePage(Page page){
        this.pages = new PageManager(page);
        this.flashPageHeading = page.locator("h1[class='font-display font-bold text-2xl md:text-3xl text-white']");
    }

    public String getFeaturePageHeading(){
        return flashPageHeading.textContent();
    }


}
