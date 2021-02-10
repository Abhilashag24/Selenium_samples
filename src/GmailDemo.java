import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailDemo {
	WebDriver driver;
	String toAddress = "addressreceiver@xxx.com";
	String subject = "WebDriver - Gmail Compose Demo";
	String userName = "demot174@gmail.com";
	
	public static void main(String[] args) throws InterruptedException {
		GmailDemo gmailDemo = new GmailDemo();
		gmailDemo.initializeBrowser();
		gmailDemo.signInToGmail();
		gmailDemo.ComposeMail();
	}

	public void initializeBrowser() {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("javascript.enabled");
		options.addArguments("--disable-web-security");
		options.addArguments("--user-data-dir");
		options.addArguments("--allow-running-insecure-content"); 
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();

		String url = "https://gmail.com";
		driver.get(url);
	}

	public void signInToGmail() {
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='identifierId']")))
				.sendKeys(userName);
		driver.findElement(By.id("identifierNext")).click();
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='password']")))
				.sendKeys("Summer@69");
		driver.findElement(By.id("passwordNext")).click();
		

	}

	public void ComposeMail() throws InterruptedException {
		
		// click on Compose button
		new WebDriverWait(driver, 20)
		.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".aic .z0 div"))).click();
		
		// enter TO address & Subject
		new WebDriverWait(driver, 10)
		.until(ExpectedConditions.visibilityOfElementLocated(By.name("to"))).sendKeys(toAddress);
		new WebDriverWait(driver, 10)
		.until(ExpectedConditions.visibilityOfElementLocated(By.name("subjectbox"))).sendKeys(subject);

		// click Send button
		new WebDriverWait(driver, 10)
		.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr.btC td:nth-of-type(1) div div:nth-of-type(2) div:nth-of-type(1)"))).click();
	
		//Logout from Gmail
		  driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div[1]/div[2]/div/a")).click();
		  driver.findElement(By.linkText("Sign out")).click();
	}
	public void finalize(){
		driver.close();
		driver.quit();
	}
}
