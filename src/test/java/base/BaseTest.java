package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseTest {
	//declare 2 methods setUp &tearDown
	protected Playwright playwright;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;
	
	@BeforeMethod
	public void SetUp() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false));
		context = browser.newContext();
		page= context.newPage();
		
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		browser.close();
		playwright.close();
	}
	

}
