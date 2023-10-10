import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.LoginPage;
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
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

public class SauceDemoTest {

    WebDriver driver;

    @BeforeAll
    static void driverSetup() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void driver() {

        driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    void inventory() {

        LoginPage page = new LoginPage(driver);

        page.inputUsername("standard_user");
        page.inputPassword("secret_sauce");
        page.buttonClick();


        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        WebElement cart = driver.findElement(By.cssSelector(".shopping_cart_link"));
        assertTrue(cart.isDisplayed());

    }

    @Test
    void wrongPassword() {

        LoginPage page = new LoginPage(driver);

        page.inputUsername("standard_user");
        page.inputPassword("wrong_password");
        page.buttonClick();

        assertEquals("Epic sadface: Username and password do not match any user in this service", driver.findElement(By.xpath("//div[@id='login_button_container']//form//h3")).getText());

    }

    @Test
    void wrongUsername() {

        LoginPage page = new LoginPage(driver);

        page.inputUsername("locked_out_user");
        page.inputPassword("secret_sauce");
        page.buttonClick();

        assertTrue(driver.findElement(By.xpath("//div[@id='login_button_container']//form//h3")).getText().contains("this user has been locked out."));
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/logins.csv", numLinesToSkip = 1)
    void parameterizedTest(String username, String password) {


        assertTimeout(ofMillis(2000), () -> {

            LoginPage page = new LoginPage(driver);

            page.inputUsername(username);
            page.inputPassword(password);
            page.buttonClick();
            assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        });
    }


}



