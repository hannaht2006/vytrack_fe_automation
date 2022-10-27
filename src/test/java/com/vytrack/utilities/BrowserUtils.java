package com.vytrack.utilities;


import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;

public class BrowserUtils {
    //methods are static type because we do not want to create an object to call this method. we just call those method with class name

    /**
     * This method is used to pause the code for given seconds
     * It is static method, we can call the method with class name as below:
     * BrowserUtils.sleep(3);
     * @param seconds
     */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
        e.printStackTrace();
            System.out.println("exception happened in sleep method");
        }
    }

    //Method info:
    //• Name: verifyTitle()
    //• Return type: void
    //• Arg1: WebDriver
    //• Arg2: String expectedTitle
    //BrowserUtils.verifyTitle(driver,"Google")

    public static void verifyTitle(String expectedTitle) {
        // replacing driver ---> Driver.getDriver()
        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertEquals(expectedTitle, actualTitle);

    }
    public void switchWindowAndVerify(String expectedInUrl, String expectedTitle){
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();
        for(String eachWindow : allWindowHandles){
            Driver.getDriver().switchTo().window(eachWindow);
            if(Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }
        }

        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals("Title verification failed", actualTitle, expectedTitle);
    }

    public void waitForInvisibilityOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
