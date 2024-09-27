package Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BusSearch {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1024,768");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testBusBooking() throws InterruptedException, AWTException {
        // Open the KSRTC website
        driver.get("https://ksrtc.in/oprs-web/guest/home.do?h=1");
        Thread.sleep(3000);

        // Select popular route from CHIKKAMAGALURU to BENGALURU
        selectCity("fromCity_chosen", "CHIKKAMAGALURU");
        selectCity("toCity_chosen", "BENGALURU");

        // Select the departure date
        driver.findElement(By.id("departDate")).click();
        pressEnterKey();

        // Submit the search
        driver.findElement(By.id("submitSearch")).click();
        Thread.sleep(3000);

        // Select a bus and proceed to select seats
        driver.findElement(By.className("selectbutton")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".seatlook")).click();

        // Select seats using the Robot class
        selectSeats(7);

        // Enter mobile number
        enterMobileNumber("6789125987");
    }

    private void selectCity(String elementId, String cityName) throws AWTException {
        driver.findElement(By.id(elementId)).click();
        typeWithRobot(cityName);
        pressEnterKey();
    }

    private void typeWithRobot(String text) throws AWTException {
        Robot robot = new Robot();
        for (char character : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(Character.toUpperCase(character));
            if (keyCode != KeyEvent.VK_UNDEFINED) {
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            }
        }
    }

    private void pressEnterKey() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private void selectSeats(int count) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        for (int i = 0; i < count; i++) {
            pressEnterKey();
            Thread.sleep(300);
        }
    }

    private void enterMobileNumber(String number) throws AWTException {
        Robot robot = new Robot();
        robot.delay(3000); // Delay to allow focus on input field

        for (char digit : number.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(digit);
            if (keyCode != KeyEvent.VK_UNDEFINED) {
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
                robot.delay(100); // Slight delay between keystrokes
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
