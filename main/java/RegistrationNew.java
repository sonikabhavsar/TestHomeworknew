import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;

import static org.openqa.selenium.By.className;

public class RegistrationNew {
    protected static WebDriver driver;
    static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    public static void clickOnElement(By by){
        driver.findElement(by).click();
    }
   public static void typeText(By by,String text){
       driver.findElement(by).sendKeys(text);
   }
   public static String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }

  public static long timestamp(){
  Timestamp timestamp =new Timestamp(System.currentTimeMillis());
  return timestamp.getTime();
}
static String expectedRegistrationCompleteMsg ="Thanks for registration";
    static String expectedCommunityPollVoteMessage ="Only registered users can vote.";
    static String expectedAbleToVoteMessage = "Only registered user can vote.";
    static String expectedCompareProductMessage ="can not comparing product";
@BeforeMethod
public static void openBrowser(){
    driver = new ChromeDriver();
    //type the url
    driver.get("https://demo.nopcommerce.com/");

}
@AfterMethod
public static void closeBrowser(){
        driver.close();
}
   @Test
   public static void verifyUserShouldBeAbleToRegisterSuccessfully(){
       //open the browser
       openBrowser();
       //click on register button
       clickOnElement(className("ico-register"));
       //type first name
       typeText(By.id("FirstName"),"TestFirstName");
       //type last name
       typeText(By.id("LastName"),"TestLstName");
       //type email address
       typeText(By.name("Email"),"testemail+" + timestamp() + "@gmail.com");
       //type password
       typeText(By.id("Password"),"test1234");
       //type confirm password
       typeText(By.id("ConfirmPassword"),"test1234");
       //click on register submit button
       clickOnElement(By.id("register-button"));
       //get text message from web element
      String actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
      System.out.println("My message:" + actualMessage);
     // Compare the Expected Result with the Actual Result
      Assert.assertEquals(actualMessage,expectedRegistrationCompleteMsg,"Registration is not working");
      // //for close browser
      // closeBrowser();
       clickOnElement(By.className("ico-login"));
       typeText(By.xpath("//div[@class='form-fields']/div[1]/input"),"testmail@gmai.com");
       typeText(By.xpath("//div[@class='form-fields']/div[2]/input"),"test1234");
       clickOnElement(By.className("button-1 login-button"));

   }
   @Test
   public static void verifyAbleToCommunity(){

       //open the browser
       openBrowser();
       //click on good button
       clickOnElement(By.id("pollanswers-2"));
       //Click on vote button
       clickOnElement(By.id("vote-poll-1"));
       //Use the selenium wait
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       //get the text message for web element
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"poll-vote-error\"]")));
       String actualMessage = getTextFromElement(By.xpath("//div[@class=\"poll-vote-error\"]"));
       System.out.println("My message:" + actualMessage);
       //text message  is disappearing
      Assert.assertEquals(actualMessage,expectedCommunityPollVoteMessage,"Error message is disappearing.");
   }
   @Test
   public static void SendEmailFriens() {
       clickOnElement(By.className("ico-register"));
       //type first name
       typeText(By.id("FirstName"), "Rahul");
       //type last name
       typeText(By.id("LastName"), "shah");
       //type email address
       typeText(By.name("Email"), "raul123@gmail.com");
       //type password
       typeText(By.id("Password"), "rahul1234");
       //type confirm password
       typeText(By.id("ConfirmPassword"), "rahul1234");
       //click on register submit button
       clickOnElement(By.id("register-button"));
       clickOnElement(By.className("ico-login"));
       typeText(By.xpath("//div[@class='form-fields']/div[1]/input"), "raul123@gmail.com");
       typeText(By.xpath("//div[@class='form-fields']/div[2]/input"), "rahul1234");
       // clickOnElement(By.className("button-1 login-button"));
       clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));

       clickOnElement(By.xpath("//a[@ href=\"/apple-macbook-pro-13-inch\"]"));
       //click on email a friend
       clickOnElement(By.className("email-a-friend"));
       //Enter friend email
       typeText(By.className("friend-email"), "raj123@gmail.com");
       //Enter your email
       typeText(By.className("your-email"), "rahul123@gmail.com");
       typeText(By.id("PersonalMessage"), "pls check this product");

       //click on send email button
       clickOnElement(By.name("send-email"));

       // String actualMessage = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
       // System.out.println("My Message:"+actualMessage);
       String actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
       System.out.println("My Message :" + actualMessage);
   }
   @Test
   public static void VerifideUserComperProduct(){
       clickOnElement(By.className("ico-register"));
       //type first name
       typeText(By.id("FirstName"),"Rahul");
       //type last name
       typeText(By.id("LastName"),"shah");
       //type email address
       typeText(By.name("Email"),"raul123@gmail.com");
       //type password
       typeText(By.id("Password"),"rahul1234");
       //type confirm password
       typeText(By.id("ConfirmPassword"),"rahul1234");
       //click on register submit button
       clickOnElement(By.id("register-button"));
       //click on login button
       clickOnElement(By.className("ico-login"));
       //Type  email address
       typeText(By.xpath("//div[@class='form-fields']/div[1]/input"),"raul123@gmail.com");
       //Type password
       typeText(By.xpath("//div[@class='form-fields']/div[2]/input"),"rahul1234");
       clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));
       //click on good button
       clickOnElement(By.id("pollanswers-2"));
      // clickOnElement(By.xpath("//input[@id='pollanswers-2'"));

       //Click on vote button
       clickOnElement(By.id("vote-poll-1"));
       String actualMessage = getTextFromElement(By.xpath("poll-vote-error"));
       System.out.println("My message:" + actualMessage);
       Assert.assertEquals(actualMessage,expectedCommunityPollVoteMessage,"Total votes are wrong.");
   }
@Test
public static void Veri(){
    clickOnElement(By.className("ico-register"));
    typeText(By.id("FirstName"), "Manhar");
    typeText(By.id("LastName"), "Arya");
    typeText(By.name("Email"), "manhararya25@gmail.com");
    typeText(By.id("Password"), "1111aaaa");
    typeText(By.id("ConfirmPassword"), "1111aaaa");
    // Complete registration
    clickOnElement(By.id("register-button"));
    clickOnElement(By.className("ico-login"));
    // Enter your email address
    typeText(By.id("Email"), "manhararya25@gmail.com");
    // Enter Password
    typeText(By.id("Password"), "1111aaaa");
    // Click Log in button
    clickOnElement(By.xpath("//div[@class=\"buttons\"]//button[@class=\"button-1 login-button\"]"));
    // Choose polling option
   // clickOnElement(By.id("pollanswers-2"));
   clickOnElement(By.id("pollanswers-2"));
    // Do Vote
    clickOnElement(By.id("vote-poll-1"));
    String actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
    System.out.println("My Message:"+actualMessage);
    // Expected message
    Assert.assertEquals(actualMessage,expectedAbleToVoteMessage, "Total Votes are wrong");
}




     public static void main(String[] args) {
            //open the browser
            driver = new ChromeDriver();

            //type the url
            driver.get("https://demo.nopcommerce.com/");
         clickOnElement(By.className("ico-register"));
         //type first name
         typeText(By.id("FirstName"),"Rahul");
         //type last name
         typeText(By.id("LastName"),"shah");
         //type email address
         typeText(By.name("Email"),"rahul123@gmail.com");
         //type password
         typeText(By.id("Password"),"rahul1234");
         //type confirm password
         typeText(By.id("ConfirmPassword"),"rahul1234");
         //click on register submit button
         clickOnElement(By.id("register-button"));
         //click on login button
         clickOnElement(By.className("ico-login"));
         //Type  email address
         typeText(By.xpath("//div[@class='form-fields']/div[1]/input"),"rahul123@gmail.com");
         //Type password
         typeText(By.xpath("//div[@class='form-fields']/div[2]/input"),"rahul1234");
         clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));
         //click on good button
         clickOnElement(By.id("pollanswers-2"));
         //Click on vote button
         clickOnElement(By.id("vote-poll-1"));
         String actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
         System.out.println("My Message:"+actualMessage);
         Assert.assertEquals(actualMessage,expectedAbleToVoteMessage,"Total Votes are wrong");






}}
