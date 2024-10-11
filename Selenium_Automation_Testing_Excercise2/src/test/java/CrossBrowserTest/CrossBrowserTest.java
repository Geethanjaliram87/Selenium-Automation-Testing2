package CrossBrowserTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrossBrowserTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // This method will be empty; the browser setup will be handled in the test method
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    public void testWebsite(String browser) {
        // Print the name of the browser being tested
        System.out.println("Starting test on browser: " + browser);

        // Set up the driver based on the browser parameter
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.35.0-win64\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "C:\\edgedriver_win64\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();

        driver.get("https://www.example.org/"); // Replace with the URL you want to test
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        // Verify title
        assertEquals("Example Domain", title, "Title verification failed!");
        System.out.println("Title verification passed!");

        tearDown(); // Clean up after test
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    static Stream<String> browserProvider() {
        return Stream.of("chrome", "firefox", "edge"); // Provide the browsers to test
    }
}
