package WaitMechanismExample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitExample {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver and open the browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to a dynamic loading page
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        try {
            // Locate and click the "Start" button to load dynamic content
            WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
            startButton.click();

            // Set an explicit wait for the dynamically loaded element to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loadedText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

            // Retrieve and print the loaded text to confirm successful wait
            String dynamicText = loadedText.getText();
            System.out.println("Loaded text: " + dynamicText);

            // Validate that the element is visible
            if (dynamicText.equals("Hello World!")) {
                System.out.println("Dynamic content loaded successfully.");
            } else {
                System.out.println("Dynamic content did not load as expected.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
