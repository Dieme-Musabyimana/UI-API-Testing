package base;

import com.microsoft.playwright.Page;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        // Extract the active Playwright page instance from your BaseTest class
        Page page = ((BaseTest) testClass).getPage();

        if (page != null) {

            // Generate a clean, unique filename using the test method name and timestamp
            String testName = result.getName();
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String screenshotPath = "target/screenshots/" + testName + "_" + timestamp + ".png";

            // Take the screenshot
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(screenshotPath))
                    .setFullPage(true)); // fullPage grabs the entire scrolling length!

            System.out.println("❌ Test failed! Failure screenshot saved safely to: " + screenshotPath);
        }
    }
}
