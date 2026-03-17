package utils;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;

// Central utility class for capturing screenshots
 
public class ScreenshotUtil {

    //Capture screenshot for given page and filename
     
    public static void capture(Page page, String fileName) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(fileName))
                .setFullPage(true));
    }
}