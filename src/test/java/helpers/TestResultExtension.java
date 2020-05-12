package helpers;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Field;

public class TestResultExtension implements TestWatcher, AfterEachCallback {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TestResultExtension.class);

    @Override
    public void testSuccessful(ExtensionContext context){
        logger.info("Test \" {} \" - PASSED",context.getDisplayName());
    }
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.info("Test \" {} \" failed due to : {}",context.getDisplayName(),cause.getMessage());
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] takeScreenshot() {
      return ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.BYTES);

    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        getDriverFieldOfTest(extensionContext);
        if(extensionContext.getExecutionException().isPresent()){
            takeScreenshot();
        }
        quitDriver();
    }

    private void getDriverFieldOfTest(ExtensionContext extensionContext){
        Object testClass = extensionContext.getRequiredTestInstance();
        Field driver;
        try {
            driver = testClass.getClass().getSuperclass().getDeclaredField("driver");
            driver.setAccessible(true);
            this.driver = (WebDriver)driver.get(testClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void quitDriver(){
        if(this.driver!=null){
            this.driver.quit();
        }
    }
}
