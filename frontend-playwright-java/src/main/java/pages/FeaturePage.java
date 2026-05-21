package pages;

import com.microsoft.playwright.Page;
import managers.PageManager;

public class FeaturePage {
    private PageManager pages;

    public FeaturePage(Page page){
        this.pages = new PageManager(page);
    }


}
