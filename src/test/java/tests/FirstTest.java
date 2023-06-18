package tests;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FirstTest extends Base {
    @Test(priority = 1)
    void loginTest() throws InterruptedException {
        //Thread.sleep(4000);
        System.out.println(driver.getWindowHandle());
        driver.findElement(By.name(getLocator().getString("username"))).sendKeys(getConfig().getString("user"));
        driver.findElement(By.name(getLocator().getString("password"))).sendKeys(getConfig().getString("pass"));
        driver.findElement(By.xpath(getLocator().getString("login_button"))).click();
        //Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle().toString(), "OrangeHRM");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    void searchAdmins() throws InterruptedException {

        driver.findElement(By.xpath(getLocator().getString("page"))).click();
        //Thread.sleep(4000);
        driver.findElement(By.xpath(getLocator().getString("role"))).click();
        //Thread.sleep(1000);
        driver.findElement(By.xpath(getLocator().getString("role"))).sendKeys(Keys.ARROW_DOWN);
        //Thread.sleep(1000);
        driver.findElement(By.xpath(getLocator().getString("role"))).sendKeys(Keys.ENTER);
        //Thread.sleep(2000);
        driver.findElement(By.xpath(getLocator().getString("search"))).click();
        Thread.sleep(3000);

        List<WebElement> admins = driver.findElements(By.xpath(getLocator().getString("rows")));
        System.out.println(admins.size());

        for(int i=1; i <= admins.size(); i++){
            String userAdmin = driver.findElement(By.xpath("//div[@class='oxd-table-body']//div["+i+"]//div[1]//div[2]")).getText();
            String employee = driver.findElement(By.xpath("//div[@class='oxd-table-body']//div["+i+"]//div[1]//div[4]")).getText();
            System.out.println(userAdmin+" --  "+employee);
        }
    }


    @Test(priority = 3, dependsOnMethods = {"loginTest"})
    void changeWindow() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //Thread.sleep(2000);
        driver.findElement(By.linkText(getLocator().getString("bottom_link"))).click();
        //Thread.sleep(5000);
        Set<String> wHandle2= driver.getWindowHandles();
        for(String x:wHandle2){
            System.out.println(x);
        }
        List<String> list = new ArrayList<String>(wHandle2);
        driver.switchTo().window(list.get(list.size()-1).toString());
        System.out.println(driver.getWindowHandle());
        //Thread.sleep(2000);

        String title2= "OrangeHRM HR Software | Free & Open Source HR Software | HRMS | HRIS | OrangeHRM";
        Assert.assertEquals(driver.getTitle().toString(),title2);
    }

    @Test(priority = 4, dependsOnMethods = {"loginTest","changeWindow"})
    void changeLanguage() throws InterruptedException {

        Actions action = new Actions(driver);
        WebElement flag = driver.findElement(By.xpath(getLocator().getString("flag")));
        action.moveToElement(flag).perform();
        driver.findElement(By.xpath(getLocator().getString("spanish"))).click();
        //Thread.sleep(5000);

        System.out.println(driver.findElement(By.xpath(getLocator().getString("mensaje"))).getText());
        Assert.assertEquals(driver.findElement(By.xpath(getLocator().getString("mensaje"))).getText(),"Recarga a tu equipo de RH y empoderalos con tu poderoso software de recursos humanos");

    }


}
