package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.beust.jcommander.WrappedParameter;

import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Assert;
import dev.failsafe.internal.util.Durations;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testCase01() throws InterruptedException {

        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textBox = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[1]")));
        Wrappers.Text(textBox, "Crio Learner");
        Thread.sleep(2000);
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea")));
        String EpochTime = Wrappers.epochTime();
        Thread.sleep(3000);
        System.out.println("wait 2");
        Wrappers.Text(textarea, "I want to be the best QA Engineer!" + EpochTime);
        Thread.sleep(3000);
        System.out.println("wait 3");
        Wrappers.radioButton(driver, "3 - 5");
        Thread.sleep(3000);
        System.out.println("wait 4");
        Wrappers.checkBox(driver, "Java");
        Wrappers.checkBox(driver, "Selenium");
        Wrappers.checkBox(driver, "TestNG");
        Thread.sleep(3000);

        WebElement dropDownElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'KKjvXb')])[1]")));
        Wrappers.clickOnElement(driver, dropDownElement);
        Thread.sleep(2000);
        System.out.println("wait 5");
        Wrappers.dropDown(driver, "Mr");
        Thread.sleep(2000);
        System.out.println("wait 6");
        WebElement dateElement = driver.findElement(By.xpath("//input[@type='date']"));
        String date = Wrappers.datesevenDaysAgo();
        Thread.sleep(2000);
        Wrappers.Text(dateElement, date);
        Thread.sleep(2000);
        System.out.println("wait 7");
        WebElement timeHour = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
        Wrappers.Text(timeHour, "07");
        Thread.sleep(2000);
        WebElement timeMinute = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
        Wrappers.Text(timeMinute, "30");
        Thread.sleep(2000);
        System.out.println("wait 8");
        WebElement submit = driver.findElement(By.xpath("//span[text()='Submit']"));
        Wrappers.clickOnElement(driver, submit);
        Thread.sleep(3000);
        System.out.println("wait 9");
        WebElement message = driver.findElement(By.xpath("//div[@class='vHW8K']"));
        String messageText = message.getText();
        Thread.sleep(2000);
        String ExpectedText = "Thanks for your response, Automation Wizard!";
        if (messageText.trim().equals(ExpectedText)) {
            System.out.println("TestCase:Pass");
        } else {
            System.out.println("Fail");
        }

    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}