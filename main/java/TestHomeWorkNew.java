
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.TestInstance;

public class TestHomeWorkNew {




    protected static WebDriver driver;
        static Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        public static void clickOnElement(By by) {
            driver.findElement(by).click();
        }

        public static void typeText(By by, String text) {
            driver.findElement(by).sendKeys(text);
        }

        public static String getTextFromElement(By by) {
            return driver.findElement(by).getText();
        }

        public static long timestamp() {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return timestamp.getTime();
        }

        static String expectedRegistrationCompleteMsg = "Thanks for registration";
        static String expectedCommunityUserPollVoteMsg = "Only registered users can vote.";
        static String expectedCompareProductMsg = "Can not Compare there product";
        static String expectedEmailToFriendMsg = "Only registered customers can use email a friend feature";
        static String expectedVoteUserMsg = "Total vote are 9";
        static String expectedReferProductMessage="Your message has been sent.";
        static String expectedShoppingCartMessage = "Leica T Mirrorless Digital Camera";

        @BeforeMethod
        public static void openBrowser() {
            driver = new ChromeDriver();
            //it will type  URL
            driver.get("https://demo.nopcommerce.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        }

        @AfterMethod
        public static void closeBrowser() {
            driver.close();
        }

        @Test
        public static void verifyUserShouldBeAbbleToRegisterSuccessFully() {

            // click on register button
            clickOnElement(By.className("ico-register"));
            // type firstname
            typeText(By.id("FirstName"), "TestFirstName");
            // type lastname
            typeText(By.name("LastName"), "TestLastName");
            // type email address // click on register button
            typeText(By.name("Email"), "test0Email+" + timestamp() + "@gmail.com");
            // type password
            typeText(By.id("Password"), "test0123");
            // type confirm password
            typeText(By.id("ConfirmPassword"), "test0123");
            // click on register submit button
            clickOnElement(By.id("register-button"));

            String actualMassage = getTextFromElement(By.xpath("//div[@class='result']"));
            System.out.println("My message:" + actualMassage);
            Assert.assertEquals(actualMassage, expectedRegistrationCompleteMsg, "Registration is not Working");
            closeBrowser();

        }

        @Test
        public static void verifyUserShouldBeAbbleToAddProductInCart() {

            //click on Electronics product
            clickOnElement(By.linkText("Electronics"));
            //click on camera
            clickOnElement(By.linkText("Camera & photo"));
            //click on leica
            clickOnElement(By.linkText("Leica T Mirrorless Digital Camera"));
            //add to cart
            clickOnElement(By.id("add-to-cart-button-16"));
            //go to shopping cart
            clickOnElement(By.linkText("Shopping cart"));
            //get massage
            String actualMassage = getTextFromElement(By.xpath("//*[@id=\"shopping-cart-form\"]/div[1]/table/tbody/tr/td[3]"));
            System.out.println("My message:" + actualMassage);
            Assert.assertEquals(actualMassage,expectedShoppingCartMessage,"Product is not adding in shopping cart");


        }

        @Test
        public static void registerUserShouldBeAbbleToVote() {
            //click on good
            clickOnElement(By.id("pollanswers-2"));
            //click on vote
            clickOnElement(By.id("vote-poll-1"));
            //get text from web elements
            //driver.findElement(By.id("block-poll-vote-error-1")).getText();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"poll-vote-error\"]")));
            String actualMessage = driver.findElement(By.xpath("//div[@class=\"poll-vote-error\"]")).getText();
            System.out.println("My message :" + actualMessage);
            Assert.assertEquals(actualMessage, expectedCommunityUserPollVoteMsg, "Error massage is disappear");
            // driver.close(); }
        }

        @Test
        public static void VerifyUserShouldBeAbbleToCopareTheTwoProduct() {
            clickOnElement(By.linkText("HTC One M8 Android L 5.0 Lollipop"));
            clickOnElement(By.xpath("//div[@class='compare-products']"));
            //select product
            clickOnElement(By.linkText("Gift Cards"));
            clickOnElement(By.linkText("$25 Virtual Gift Card"));
           // clickOnElement(By.xpath("//div[@class=compare-products"));
            clickOnElement(By.xpath("//div[@class='compare-products']/button"));
            //product comparison
            clickOnElement(By.linkText("product comparison"));
            String actualMessage1 = getTextFromElement(By.linkText("HTC One M8 Android L 5.0 Lollipop"));
            System.out.println("First Product" + actualMessage1);
            String actualMessage2 = getTextFromElement(By.linkText("$25 Virtual Gift Card"));
            System.out.println("Second product" + actualMessage2);
            clickOnElement(By.className("clear-list"));
            String actualMessage3 = getTextFromElement(By.xpath("//div[@class=\"no-data\"]"));
            System.out.println("My message " + actualMessage3);
            Assert.assertEquals(actualMessage3,expectedCompareProductMsg, "Can not Compare there product");
        }

        @Test
        public static void VerifyUserShouldEmailFriend() {

            //click on add to cart button on Apple Macbook pro 13
            clickOnElement(By.xpath("//a[@href=\"/apple-macbook-pro-13-inch\"]"));
            // Email a Friend
            clickOnElement(By.className("email-a-friend"));
            //type friends email
            typeText(By.className("friend-email"), "test122email+" + timestamp() + "@gmail.com");
            //type your email
            typeText(By.id("YourEmailAddress"), "test123email" + timestamp() + "@gmail.com");
            //get personal message
            typeText(By.id("PersonalMessage"), "Hello");
            clickOnElement(By.name("send-email"));
           // String actualMessage = getTextFromElement(By.className("//div[@class='page-body']/form/div[1]"));
            String actualMessage = getTextFromElement(By.xpath("//div[@class='message-error validation-summary-errors']/ul/li"));
            System.out.println("My Message:" + actualMessage);
            Assert.assertEquals(actualMessage,expectedEmailToFriendMsg, "Can not sending email to friend");
        }

        @Test
        public static void verifyUserShouldBeAbbleToVoteSuccesfully() {
            // click on register icon
            clickOnElement(By.className("ico-register"));
            // click on first name & filled user name
            typeText(By.id("FirstName"), "JAHANVE");
            //click on last name and then get entered
            typeText(By.id("LastName"), "PATEL");
            //click on email and filled email address
            typeText(By.id("Email"), "Jahanve12@gmail.co.uk");
            //click on password and filled it
            typeText(By.id("Password"), "jahanve123");
            typeText(By.id("ConfirmPassword"), "jahanve123");
            clickOnElement(By.id("register-button"));
            //click on login
            clickOnElement(By.className("ico-login"));
            //click on email
            typeText(By.id("Email"), "Jahanve12@gmail.co.uk");
            //type password
            typeText(By.id("Password"), "jahanve123");
            //click on login
            clickOnElement(By.xpath("//button[@class='button-1 login-button']"));
            //click on Excellent
           // clickOnElement(By.id("//input[@id='pollanswers-1']"));
            clickOnElement(By.id("pollanswers-1"));
            // click on vote
            clickOnElement(By.id("vote-poll-1"));
            String actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
            System.out.println("My message" + actualMessage);
            Assert.assertEquals(actualMessage,expectedVoteUserMsg, "Total vote are wrong");
        }
        @Test
        public static void VerifyUserShouldBeAbbleToReferProductToFriend(){

            clickOnElement(By.className("ico-register"));
            //type first name
            typeText(By.id("FirstName"), "Vaidehi");
            //type last name
            typeText(By.id("LastName"), "patel");
            //type email address
            typeText(By.name("Email"), "Vaidehi@gmail.com");
            //type password
            typeText(By.id("Password"), "dell1");
            //type confirm password
            typeText(By.id("ConfirmPassword"), "dell1");
            //click on register submit button
            clickOnElement(By.id("register-button"));
            //click on login button
            clickOnElement(By.className("ico-login"));
            //Type  email address
            typeText(By.xpath("//div[@class='form-fields']/div[1]/input"), "rahul123@gmail.com");
            //Type password
            typeText(By.xpath("//div[@class='form-fields']/div[2]/input"), "rahul1234");
            clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));
            //select product
            clickOnElement(By.xpath("//a[@ href=\"/apple-macbook-pro-13-inch\"]"));
            //click on email a friend
            clickOnElement(By.className("email-a-friend"));
            //Enter friend email
            typeText(By.className("friend-email"), "OPPS@gmail.com");
            //type the msg
            typeText(By.id("PersonalMessage"), "pls check this product");
            //click on send email button
            clickOnElement(By.name("send-email"));
            String actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
            System.out.println("My Message:" + actualMessage);
            Assert.assertEquals(actualMessage, expectedReferProductMessage, "Successfully message send");
        }


    }


