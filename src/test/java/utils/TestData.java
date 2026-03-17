package utils;

import org.testng.annotations.DataProvider;

public class TestData {

    // Users for login
    @DataProvider(name = "users")
    public static Object[][] getUsers() {
        return new Object[][] {
            { "standard_user", "secret_sauce" },
            //{ "problem_user", "secret_sauce" },
            //{ "performance_glitch_user", "secret_sauce" }
        };
    }

    // Checkout information
    @DataProvider(name = "checkoutData")
    public static Object[][] getCheckoutData() {
        return new Object[][] {
            { "Test", "User", "00100" } // First Name, Last Name, Zip Code
        };
    }
}