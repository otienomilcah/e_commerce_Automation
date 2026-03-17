package tests;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.annotations.*;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.YourCartPage;
import pages.CheckoutPage;
import utils.ScreenshotUtil;
import utils.TestData;

// Full end-to-end SauceDemo purchase flow test
// Includes retry, screenshots, and data-driven checkout
public class SaucedemoTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private YourCartPage yourCartPage;
    private CheckoutPage checkoutPage;

    @DataProvider(name = "users")
    public Object[][] getUsers() {
        return TestData.getUsers(); // pull users from utils
    }

    @BeforeMethod
    public void setUpTest() {
        setUp("chromium"); // browser setup
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        yourCartPage = new YourCartPage(page);
        checkoutPage = new CheckoutPage(page);
    }

    @Test(dataProvider = "users", retryAnalyzer = utils.RetryAnalyzer.class)
    public void verifyFullPurchaseFlow(String username, String password) {
        try {
            //  Navigate and login
            loginPage.navigate();
            loginPage.login(username, password);
            assertTrue(loginPage.isLoginSuccessful(), "Login failed for user: " + username);
            assertEquals(productsPage.getTitle(), "Products");

            //  Add first 2 products to cart
            productsPage.addFirstNProductsToCart(2);
            assertEquals(productsPage.getCartBadgeCount(), 2, "Cart badge count mismatch");

            //  Open cart + verify items
            productsPage.openCart();
            List<String> cartItems = yourCartPage.getCartItemNames();
            assertEquals(cartItems.size(), 2, "Cart should contain 2 items");

            //  Checkout form using data from TestData
            yourCartPage.clickCheckout();
            Object[][] checkoutInfo = TestData.getCheckoutData();
            String firstName = (String) checkoutInfo[0][0];
            String lastName = (String) checkoutInfo[0][1];
            String zip = (String) checkoutInfo[0][2];

            checkoutPage.fillCheckoutForm(firstName, lastName, zip);
            checkoutPage.clickContinue();

            //  Validate total price including tax (assuming 8%)
            List<Double> itemPrices = checkoutPage.getItemPrices();
            double expectedTotal = itemPrices.stream().mapToDouble(Double::doubleValue).sum() * 1.08;
            double displayedTotal = checkoutPage.getDisplayedTotal();
            assertEquals(displayedTotal, expectedTotal, 0.01, "Total price mismatch");

            //  Complete checkout and verify confirmation
            checkoutPage.clickFinish();
            assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!",
                    "Order confirmation message mismatch");
            
        } catch (Exception e) {
            ScreenshotUtil.capture(page, "failure_" + username + ".png");
            throw e;
        }
    }
}