package pages;

import com.microsoft.playwright.Page;

//Page Object Model for login page
 
public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    // Navigate to SauceDemo
     
    public void navigate() {
        page.navigate("https://www.saucedemo.com/");
    }

    //Fill login credentials and submit
     
    public void login(String username, String password) {
        page.getByPlaceholder("Username").fill(username);
        page.getByPlaceholder("Password").fill(password);
        page.locator("#login-button").click();
    }

   //Validate login success by URL and page title
     
    public boolean isLoginSuccessful() {
        return page.url().contains("/inventory") &&
               page.locator(".title").innerText().equals("Products");
    }
}