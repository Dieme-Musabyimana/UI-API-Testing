package pages;

import com.microsoft.playwright.Page;
import managers.PageManager;

public class WishListPage {
    private PageManager pages;

    public WishListPage(Page page){
        this.pages = new PageManager(page);
    }


}
