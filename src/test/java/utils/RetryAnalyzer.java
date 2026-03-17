package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

// Retry failed tests up to 3 additional attempt
 
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetry = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetry) {
            retryCount++;
            return true;
        }
        return false;
    }
}