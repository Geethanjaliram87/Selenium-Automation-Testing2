package PageNavigationExample;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageNavigationExample {
    public static void main(String[] args) {
        // Set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            // 1. Open the W3Schools homepage
            driver.get("https://www.w3schools.com");
            String homepageTitle = driver.getTitle();
            System.out.println("Homepage Title: " + homepageTitle);

            // 2. Navigate to the "Learn HTML" page
            WebElement learnHtmlLink = driver.findElement(By.linkText("Learn HTML"));
            learnHtmlLink.click();

            // Wait for the page to load
            Thread.sleep(2000);

            // Verify the new page title
            String htmlPageTitle = driver.getTitle();
            System.out.println("HTML Page Title: " + htmlPageTitle);

            // 3. Navigate back to the homepage
            driver.navigate().back();
            Thread.sleep(2000);

            // Verify that we're back on the homepage
            String backPageTitle = driver.getTitle();
            if (backPageTitle.equals(homepageTitle)) {
                System.out.println("Navigated back successfully to: " + backPageTitle);
            } else {
                System.out.println("Back navigation failed.");
            }

            // 4. Navigate forward to the "Learn HTML" page again
            driver.navigate().forward();
            Thread.sleep(2000);

            // Verify that we're back on the "Learn HTML" page
            String forwardPageTitle = driver.getTitle();
            if (forwardPageTitle.equals(htmlPageTitle)) {
                System.out.println("Navigated forward successfully to: " + forwardPageTitle);
            } else {
                System.out.println("Forward navigation failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the driver
            driver.quit();
        }
    }
}

