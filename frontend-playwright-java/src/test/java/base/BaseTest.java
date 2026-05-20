package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext; // Fixed typo here
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import constants.Data;
import managers.PageManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected PageManager pages;

    @BeforeMethod
    public void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate(Data.URL);
        this.pages = new PageManager(page);
    }

    @AfterMethod
    public void close(){
        if(page != null){ page.close(); }
        if(context != null){ context.close(); }
        if(browser != null){ browser.close(); }
        if(playwright != null){ playwright.close(); }
    }
}