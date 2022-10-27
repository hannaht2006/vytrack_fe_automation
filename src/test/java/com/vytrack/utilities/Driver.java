package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    //creating a private constructor, we are closing access to the object of this class from outside
    private Driver(){}

    //creating our "driver" instance private --> not reachable from outside class. made it static because want to use it in static method
    private static WebDriver driver;

    /*
    creating re-usable utility method which same driver instance when we call it
     */
    public static WebDriver getDriver(){

        if(driver==null){ //if driver/browser was never opened
            String browserType = ConfigurationReader.getProperty("browser");
            /*
            depending on browser type our switch statement will determine to open specific type of browser/driver
             */
            switch(browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                 break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

            }
        }
        //same driver instance will be return every time we call Driver.getDriver() method
        return driver;
    }

    public static void closeDriver(){
        if(driver!= null){
            driver.quit(); //this line will kill the session. value will now be null
            driver = null;
        }
    }

}
