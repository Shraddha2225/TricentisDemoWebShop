package stepdefination;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
public class StepDef {

    WebDriver driver;
    String url = "http://demowebshop.tricentis.com/";
    String email = "shr2225@gmail.com";
    String pass = "NaviMumbai@123";
    Scenario scenario;

    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
        scenario.log("executed before each scenario");
    }
    @After
    public  void cleanUp(){
        if (!(driver==null)){
            driver.quit();
        }
        scenario.log("executed after each scenario");
    }

    @BeforeStep
    public  void beforeEachStep(){
        scenario.log("executed before each line of test case");
    }

    @AfterStep
    public void afterEachStep(){
        scenario.log("executed after each line of test case");
        if(!(driver==null)){
            TakesScreenshot screenshot =(TakesScreenshot)driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data,"image/png/jpeg","Failed step name" +scenario.getName());
            //scenario.log("Test cases is passed,no screenshots captured");

        }
        log.debug("Each step hook is executed,screen shots taken");
    }

    //TC1:User registration
    @Given("User open the browser")
    public void user_open_the_browser() {
        driver = new ChromeDriver();
        log.debug("initialized chrome");
        driver.manage().window().maximize();
        log.debug("Maximized windows");
        driver.manage().deleteAllCookies();
        log.debug("delete all cookies");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        log.debug("Each step hook is executed,screen shots taken");
    }
    @When("User navigate to the url and click on register")
    public void user_navigate_to_the_url_and_click_on_register() {
        driver.get(url);
        log.debug("navigate to the url");
        driver.findElement(By.className("ico-register")).click();
    }
    @When("User have to fill registration form as per below list")
    public void user_have_to_fill_registration_form_as_per_below_list(Map<String,String> userCred) {
        driver.findElement(By.id("gender-female")).click();
        driver.findElement(By.id("FirstName")).sendKeys(userCred.get("FirstName"));
        driver.findElement(By.id("LastName")).sendKeys(userCred.get("LastName"));
        driver.findElement(By.id("Email")).sendKeys(userCred.get("Email"));
        driver.findElement(By.id("Password")).sendKeys(userCred.get("Password"));
        driver.findElement(By.id("ConfirmPassword")).sendKeys(userCred.get("ConfirmPassword"));

    }
    @When("Click on Register button")
    public void click_on_register_button() {
        driver.findElement(By.id("register-button")).click();
    }
    @Then("Message display your registration completed")
    public void message_display_your_registration_completed(){
        WebElement expectedSubmitResultPage = driver.findElement(By.xpath("//h1[text()='Register']"));
        Assert.assertEquals(expectedSubmitResultPage.isDisplayed(),true,"your registration completed");
    }

    //TC2:login using credentials
    @Given("User opened the browser")
    public void user_opened_the_browser() {
        user_open_the_browser();
    }
    @When("User navigate to the url and click on login link")
    public void user_navigate_to_the_url_and_click_on_login_link() {
        driver.get(url);
        log.debug("navigate to the url");
        //driver.findElement(By.className("ico-logout")).click();
        driver.findElement(By.className("ico-login")).click();
    }
    @When("User enter credentials Email as {string} and password as {string}")
    public void user_enter_credentials_email_as_and_password_as(String email, String pass) {
        driver.findElement(By.id("Email")).sendKeys(email);
        log.debug("enter email" +email);
        driver.findElement(By.id("Password")).sendKeys(pass);
        log.debug("enter password" +pass);

    }
    @When("user click login button")
    public void user_click_login_button() {
        driver.findElement(By.xpath("//input[@class='button-1 login-button' and @value='Log in']")).click();
    }
    @Then("User login successfully by their registered email")
    public void user_login_successfully_by_their_registered_email() {
        WebElement expectedSubmitResultPage = driver.findElement(By.xpath("//h2[@class='topic-html-content-header']"));
        Assert.assertEquals(expectedSubmitResultPage.isDisplayed(),true,"Welcome to our store");
    }
}
