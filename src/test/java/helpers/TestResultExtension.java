package helpers;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class TestResultExtension implements TestWatcher {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TestResultExtension.class);

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.info("Test \" {} \" failed due to : {}",context.getDisplayName(),cause.getMessage());
        Object testClass = context.getRequiredTestInstance();
        Field driver;
        try {
            driver = testClass.getClass().getSuperclass().getDeclaredField("driver");
            driver.setAccessible(true);
            this.driver = (WebDriver)driver.get(testClass);
        } catch (Exception e) {
            e.printStackTrace();
        }


        File SrcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(SrcFile, new File("error.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
