package BasicBrowserAutomation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicBrowserAutomation {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Create an instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Open a browser and navigate to Google
            driver.get("https://www.google.com");

            // Maximize the browser window
            driver.manage().window().maximize();

            // Retrieve the page title
            String pageTitle = driver.getTitle();
            System.out.println("Page Title: " + pageTitle);

            // Verify that the page title matches the expected title
            String expectedTitle = "Google";
            if (pageTitle.equals(expectedTitle)) {
                System.out.println("Title verification passed: " + pageTitle);
            } else {
                System.out.println("Title verification failed: Expected '" + expectedTitle + "' but found '" + pageTitle + "'");
            }
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

