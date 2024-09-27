package Task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AmazonAutomation {

    private ChromeDriver driver;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1024,768");
        driver = new ChromeDriver(options);
    }

    // Scenario 1: Search for car accessories and add to cart
    @Test(priority = 1)
    public void testScenario1() throws InterruptedException {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);

        driver.get("https://www.amazon.eg/-/en/ref=nav_logo");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("car accessories");
        driver.findElement(By.id("nav-search-submit-button")).click();

        Thread.sleep(3000); // Consider using WebDriverWait instead
        List<WebElement> products = driver.findElements(By.cssSelector(".s-main-slot .s-result-item"));
        products.get(2).click();
        driver.findElement(By.id("add-to-cart-button")).click();
        driver.findElement(By.id("nav-cart")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".sc-product-title")).isDisplayed(), "Item not found in cart");
    }

    // Scenario 2: Access Today's Deals and add a grocery item to cart
    @Test(priority = 2)
    public void testScenario2() throws InterruptedException {
        driver.get("https://www.amazon.eg/-/en/ref=nav_logo");
        driver.findElement(By.linkText("Today's Deals")).click();
        driver.findElement(By.linkText("See more")).click();

        driver.findElement(By.xpath("//span[contains(text(), 'Grocery')]")).click();
        WebElement discountElement = driver.findElement(By.xpath("//span[contains(text(),'10% off or more')]"));
        driver.executeScript("arguments[0].click();", discountElement);

        Thread.sleep(3000); // Consider using WebDriverWait instead
        driver.findElement(By.xpath("//*[@data-test-index='1']")).click();
        driver.findElement(By.id("add-to-cart-button")).click();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
