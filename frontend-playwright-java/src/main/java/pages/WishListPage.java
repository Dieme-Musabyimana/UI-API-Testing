package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WishListPage {
    private Page page;
    private final Locator wishListPageHeading;

    public WishListPage(Page page){
        this.page = page;
        this.wishListPageHeading = page.locator(".font-display.font-bold.text-3xl.text-white");
    }

    public String getWishListHeading(){
        return wishListPageHeading.textContent();

    }


}
