package FormSubmissionExample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormSubmissionExample {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Set the path for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Set timeouts
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void testFormSubmission() {
        // Open the Automation Practice Form
        driver.get("https://demoqa.com/automation-practice-form");

        // Fill out the form fields
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        firstName.sendKeys("Jane");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName")));
        lastName.sendKeys("Doe");

        WebElement mobileNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNumber")));
        mobileNumber.sendKeys("1234567890");

        WebElement genderRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='gender-radio-2']"))); // Female
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderRadio);

        WebElement currentAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentAddress")));
        currentAddress.sendKeys("123 Main Street");

        // Select date of birth
        WebElement dobInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("dateOfBirthInput")));
        dobInput.click();
        WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.className("react-datepicker__month-select")));
        new Select(monthDropdown).selectByVisibleText("May");
        WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.className("react-datepicker__year-select")));
        new Select(yearDropdown).selectByVisibleText("1995");
        WebElement day = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@aria-label, 'Choose Monday, May 15th')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", day);

        // Select subject
        WebElement subjectInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subjectsInput")));
        subjectInput.sendKeys("English");
        subjectInput.sendKeys(Keys.ENTER);

        // Select a hobby checkbox
        WebElement hobbiesCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='hobbies-checkbox-2']"))); // Reading
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbiesCheckbox);

        // Upload a picture
        WebElement uploadPicture = wait.until(ExpectedConditions.elementToBeClickable(By.id("uploadPicture")));
        uploadPicture.sendKeys("C:\\Users\\Dr. T.P. YOKESH\\OneDrive\\Desktop\\Picture1.jpg");

        // Select state
        WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("state")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateDropdown);
        stateDropdown.click();
        WebElement stateOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='NCR']")));
        stateOption.click();

        // Select city
        WebElement cityDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("city")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityDropdown);
        cityDropdown.click();
        WebElement cityOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Delhi']")));
        cityOption.click();

        // Submit the form
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

        // Verify submission success message
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        String successMessage = modal.getText();
        boolean formSubmitted = successMessage.contains("Thanks for submitting the form");

        // Output the result to the console
        System.out.println(formSubmitted ? "Form submitted successfully! " : "Form submission failed.");

        // Assert for test result
        assertTrue(formSubmitted, "Expected success message not found.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
