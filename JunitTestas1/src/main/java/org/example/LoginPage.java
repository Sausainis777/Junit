package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    By inputUser = By.id("user-name");
    By inputPassword = By.id("password");
    By clickButton = By.cssSelector("#login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void inputUsername(String username){
        WebElement input = driver.findElement(inputUser);
        input.sendKeys(username);
    }
    public void inputPassword(String password){
        WebElement input = driver.findElement(inputPassword);
        input.sendKeys(password);
    }
    public void buttonClick (){

        WebElement login = driver.findElement(clickButton);
        login.click();
    }
}
