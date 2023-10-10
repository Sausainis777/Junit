import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpencartTesting2 {

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
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#button-list > .fa-th-list.fas")));
        driver.findElement(By.cssSelector("button#button-list > .fa-th-list.fas")).click();


        WebElement iPodClassic = driver.findElement(By.linkText("iPod Classic"));
        assertTrue(iPodClassic.isDisplayed());

       iPodClassic.click();
    }
}