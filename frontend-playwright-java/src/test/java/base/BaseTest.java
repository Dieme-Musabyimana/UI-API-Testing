package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext; // Fixed typo here
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import constants.Assertions;
import constants.Data;
import constants.Locators;
import managers.PageManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected PageManager pages;
    protected Locators locators;
    protected Assertions assertions;

    @BeforeMethod
    public void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate(Data.URL);
        this.pages = new PageManager(page);
        this.locators = new Locators(page);
        this.assertions = new Assertions(this.locators);
    }

    @AfterMethod
    public void close(){
        if(page != null){ page.close(); }
        if(context != null){ context.close(); }
        if(browser != null){ browser.close(); }
        if(playwright != null){ playwright.close(); }
    }
}