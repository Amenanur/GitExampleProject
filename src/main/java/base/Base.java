package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

import java.time.Duration;

public class Base {

WebDriver driver;

    WebDriverWait wait;

    //    @BeforeTest
    @BeforeGroups
    public  void setup() {
        System.out.println("setup print");
        String driverName = "chrome";
        if (driverName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\nurpo\\Downloads\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (driverName.equals("gecko")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\nurpo\\Downloads\\Drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (driverName.equals("ie")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\nurpo\\Downloads\\Drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(200));

    }
    //   @AfterTest
    @AfterGroups

    public void close(){
        driver.close();
        System.out.println("closed browser");
    }


}
