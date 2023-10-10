import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class OpencartTesting {

    WebDriver driver;

    @BeforeAll
    static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void driver() {

        driver = new ChromeDriver();
    }

    @Test
    void productDisplayed() {
        driver.get("https://demo.opencart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector(".dropdown:nth-of-type(8) [data-bs-toggle]")).click();
        driver.findElement(By.cssSelector(".dropdown-menu.show > .see-all")).click();



        WebElement iPodClassic = driver.findElement(By.linkText("iPod Classic"));
        assertTrue(iPodClassic.isDisplayed());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", iPodClassic);

        iPodClassic.click();

        driver.findElement(By.cssSelector(".btn-group .fa-heart")).click();
        assertEquals("You must login or create an account to save iPod Classic to your wish list!",driver.findElement(By.cssSelector(".alert-dismissible")).getText());
        driver.navigate().refresh();
        driver.findElement(By.cssSelector("#button-cart")).click();
        assertEquals("Success: You have added iPod Classic to your shopping cart!",driver.findElement(By.cssSelector("div#alert > .alert.alert-dismissible.alert-success")).getText());
        driver.findElement(By.cssSelector(".btn-inverse")).click();

    }


}
