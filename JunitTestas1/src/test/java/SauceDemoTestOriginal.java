import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

public class SauceDemoTestOriginal {

    WebDriver driver;

    @BeforeAll
    static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void driver() {

        driver = new ChromeDriver();
    }

    @Disabled
    @Test
    void inventory() {


        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement login = driver.findElement(By.cssSelector("#login-button"));
        login.click();

        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        WebElement cart = driver.findElement(By.cssSelector(".shopping_cart_link"));
        assertTrue(cart.isDisplayed());

    }

    @Disabled
    @Test
    void wrongPassword() {

        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("wrong_password");

        driver.findElement(By.cssSelector("#login-button")).click();

        assertEquals("Epic sadface: Username and password do not match any user in this service", driver.findElement(By.xpath("//div[@id='login_button_container']//form//h3")).getText());

    }

    @Disabled
    @Test
    void wrongUsername() {
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("locked_out_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        driver.findElement(By.cssSelector("#login-button")).click();

        assertTrue(driver.findElement(By.xpath("//div[@id='login_button_container']//form//h3")).getText().contains("this user has been locked out."));
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/logins.csv", numLinesToSkip = 1)
    void parameterizedTest(String username, String password) {


        assertTimeout(ofMillis(2000), () -> {

            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.cssSelector("#login-button")).click();
            assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        });
    }


}





