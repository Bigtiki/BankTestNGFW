package Pages;

import Utility.ReadProperties;
import Utility.SharedConfig;
import com.sun.javafx.runtime.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Base {
    static WebDriver driver;

    public static WebDriver getDriver(){

        if (driver == null){

            // Setup Chrome Driver so it can work in all place
            System.out.println("Driver Starting....");

            // Sauce Variables
            String username = SharedConfig.config.get("Sauce-username");
            String accesskey = SharedConfig.config.get("Sauce-accesskey");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
            capabilities.setCapability(CapabilityType.VERSION, "latest");
            capabilities.setCapability(CapabilityType.PLATFORM, "Windows 10");


            if(SharedConfig.config.get("seleniumEnvironment").trim().toLowerCase().equals("grid")) {
                // Code to send all test to Sauce Lab
                try {
                    driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + "@ondemand.saucelabs.com:443/wd/hub"), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }else if(SharedConfig.config.get("seleniumEnvironment").trim().toLowerCase().equals("local")) {

                String os = System.getProperty("os.name").toLowerCase();
                System.out.println(os);

                if(os.contains("mac")) {
                    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//chromedriver");
                    driver = new ChromeDriver();
                }else if(os.contains("windows")) {
                    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
                    driver = new ChromeDriver();

                }
            }
        }
        return driver;
    }
    @BeforeTest
    public  void  rampUp() {

        System.out.println("This is a banking demo test");
    }
    @AfterTest
    public  void  rampDown() {

         driver.close();
        System.out.println("Driver is closed....");
    }
}
