import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class SeleniumFirst {
    protected static WebDriver driver;
static Timestamp timestamp =new Timestamp(System.currentTimeMillis());


    public static void main(String[] args) {
        //open the browser
        driver =new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //type the url
        driver.get("https://demo.nopcommerce.com/");
        //click on register button
        driver.findElement(By.className("ico-register")).click();
        //type first name
        driver.findElement(By.id("FirstName")).sendKeys("TestFirstName");
        //type last name
        driver.findElement(By.id("LastName")).sendKeys("TestLstName");
        //type email address
        driver.findElement(By.name("Email")).sendKeys("testemail+"+timestamp.getTime()+"@gmail.com");
        //type password
        driver.findElement(By.id("Password")).sendKeys("test1234");
        //type confirm password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("test1234");
        //click on register submit button
        driver.findElement(By.id("register-button")).click();
        //get text message from web element
        String actulMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
        System.out.println("My message:"+ actulMessage);
        //for close browser
        driver.close();
    }
}
