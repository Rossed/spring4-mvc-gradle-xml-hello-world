package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.TestCase;

//public class FirstTestCase {
//	 
//	public static void main(String[] args) throws InterruptedException {
//		
//		// Create a new instance of the Chrome driver
//		WebDriver driver = new ChromeDriver();
//		
//        //Launch the Online Store Website
//		driver.get("http://localhost:9999/spring4-mvc-gradle-xml-hello-world/");
// 
//        // Print a Log In message to the screen
//        System.out.println("Successfully opened the website");
// 
//		//Wait for 5 Sec
//		Thread.sleep(5);
//		
//        // Close the driver
//        driver.quit();
//    }
//}

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

@RunWith(BlockJUnit4ClassRunner.class)
public class ChromeTest extends TestCase {
	
	  private static ChromeDriverService service;
	  private WebDriver driver;

	  @BeforeClass
	  public static void createAndStartService() throws IOException {
	    service = new ChromeDriverService.Builder()
	        .usingDriverExecutable(new File("/Users/rossedwa/Documents/Programs/WebDriver/chromedriver"))
	        .usingAnyFreePort()
	        .build();
	    service.start();
	  }

	  @AfterClass
	  public static void createAndStopService() {
	    service.stop();
	  }

	  @Before
	  public void createDriver() {
	    driver = new RemoteWebDriver(service.getUrl(),
	        DesiredCapabilities.chrome());
	  }

	  @After
	  public void quitDriver() {
	    driver.quit();
	  }

	  @Test
	  public void testGoogleSearch() {
		  driver.get("http://localhost:9999/spring4-mvc-gradle-xml-hello-world/");
	    // rest of the test...
		  WebElement nameInputField = driver.findElement(By.id("titlename"));
		  assertEquals(nameInputField, "Hello world");
		 
	  }

	
}
