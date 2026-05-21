package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import managers.PageManager;

public class HomePage {
    private PageManager pages;
    private final Locator usertIcn;
    private final Locator logoutBtn;
    private final Locator homeLink;
    private final Locator shopLink;
    private final Locator flashLink;
    private final Locator featureLink;
    private final Locator whishListIcn;



    public HomePage(Page page){

        this.pages = new PageManager(page);
        this.logoutBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign out"));        this.usertIcn = page.locator(".lucide.lucide-user");
        this.homeLink = page.locator("nav a", new Page.LocatorOptions().setHasText("home"));
        this.shopLink = page.locator("nav a", new Page.LocatorOptions().setHasText("shop"));
        this.flashLink = page.locator("nav a", new Page.LocatorOptions().setHasText("flash"));
        this.featureLink = page.locator("nav a", new Page.LocatorOptions().setHasText("feature"));
        this.whishListIcn = page.locator("svg.lucide-heart");

    }
    public MainPage signOut(){

        usertIcn.click();
        logoutBtn.click();
        return pages.getMainPage();
    }

    public HomePage navigateToHome(){
        homeLink.click();
        return pages.getHomePage();
    }

    public ProductPage navigateProductPage(){
        shopLink.click();
        return pages.getProductPage();
    }

    public FlashPage navigatToFlashPage(){
        flashLink.click();
        return pages.getFlashPage();
    }

    public FeaturePage navigateToFeature(){
        featureLink.click();
        return pages.getFeaturePage();
    }

    public WishListPage navigateToWishList(){
        whishListIcn.click();
        return pages.getWishListPage();
    }

}
