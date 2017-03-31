import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
 
/**
 * Simple web test that just queries the login page through the controller.
 * @author Florian Hopf, Synyx GmbH & Co. KG, hopf@synyx.de
 */
public class WebTest {
 
    @Test
    public void testPage() {
        WebDriver driver = new HtmlUnitDriver();
 
        driver.get("http://localhost:9090/spring4");
 
        try {
        	
            // Find the text input element by its name
            WebElement element = driver.findElement(By.id("link"));
 
            assertNotNull(element);
        } catch (NoSuchElementException ex) {
            fail("Startup of context failed. See console output for more information, : " + ex.getMessage());
        }
 
        //Close the browser
        driver.quit();
    }
}