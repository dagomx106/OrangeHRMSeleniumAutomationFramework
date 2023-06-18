package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ResourceBundle;

public class Base {

    public static WebDriver driver;
    public static ResourceBundle getConfig() {
        ResourceBundle config = ResourceBundle.getBundle("config");
        return config;
    }

    public static ResourceBundle getLocator() {
        ResourceBundle loc = ResourceBundle.getBundle("locator");
        return loc;
    }
    @BeforeClass
    public void setup(){
        if(getConfig().getString("browser").equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (getConfig().getString("browser").equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }else
            System.out.println("Invalid browser");

        driver.manage().window().maximize();
        driver.get(getConfig().getString("testurl"));

    }


@AfterClass
    public void tearDown(){
        driver.quit();
}


}

