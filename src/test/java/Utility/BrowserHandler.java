package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserHandler {

    public static WebDriver getBrowserInstance(String browser) {
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("ie")) {
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverManager.firefoxdriver().setup();
                return new EdgeDriver();
            }
        }catch(Exception e){
            System.out.println("browser name is wrong");
        }
        return null;
    }
}
