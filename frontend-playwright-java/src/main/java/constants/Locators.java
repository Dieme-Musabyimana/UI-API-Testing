package constants;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class Locators {
    private final Page page;
    public final Locator loginBtn;
    public final Locator startShoppingBtn;
    public final Locator createAccountBtn;

    public final Locator minusQtyBtn;
    public final Locator plusQtyBtn;
    public final Locator qtyInput;
    public final Locator productQnty;
    public final Locator product;
    public final Locator productTitle;
    public final Locator productDescr;
    public final Locator addToCartBtn;
    public final Locator color;
    public final Locator size;
    public final Locator vewCartBtn;
    public final Locator checkoutBtn;
    public final Locator addToCartPopUP;


    public Locators(Page page) {
        this.page = page;
        this.loginBtn = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in"));
        this.startShoppingBtn = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Start Shopping"));
        this.createAccountBtn = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Create account — free"));

        this.minusQtyBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-"));
        this.plusQtyBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+"));
        this.qtyInput = page.locator("input[type='number']");
        this.productQnty = page.locator(".font-display.font-bold.text-4xl.text-white");
        this.productTitle = page.locator(".font-display.font-bold.text-3xl.text-white.mt-1.leading-tight");
        this.product = page.locator("a:has-text('" + Data.productName + "')");
        this.productDescr = page.locator(".text-brand-text.text-sm.pb-4.leading-relaxed");
        this.addToCartBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart"));
        this.color = page.locator("button[title]");
        this.size = page.locator(".size-selector button, [class*='size'] button, [data-testid*='size']");
        this.vewCartBtn = page.locator("a:has-text('View Cart')");
        this.checkoutBtn = page.locator(".btn-primary.text-center.text-sm.py-3");
        this.addToCartPopUP = page.locator("div[class*='max-w-md']");
    }

}

