package stepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginStepDefination {
	private WebDriver driver;
	private LoginPage loginPage;

	@Before
	public void setUp () {
		
		System.setProperty("Webdriver.chrome.driver",".\\src\\test\\java\\resources\\chromedriver");
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		driver.manage().window().maximize();
	}
	
		@Given("I am on the login page")
		public void iAmOnTheLoginPage() {
			driver.get("https://practicetestautomation.com/practice-test-login/");
		}
		
		@When("I enter username {string} and password {string}")
		public void usrAndPass(String username, String password) {
			loginPage.enterUsername(username);
			loginPage.enterPassword(password);	
		}
		
		@When("I click on the submit button")
		public void iClickedSubmitButton() {
			loginPage.enterSubmitButton();
		}
		
		@Then("I should see the welcome message")
		public void welcomeMessage() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getSuccessMessageLocator()));
			
			String successMessage = loginPage.getSuccessMessage();
			
			Assert.assertEquals("Logged In Successfully", successMessage);
			
			System.out.println("Login Successful!");
				
		}
		
		@After
		public void teardown() {
			if (driver !=null) {
				driver.quit();
			}
		}
		
	
}
