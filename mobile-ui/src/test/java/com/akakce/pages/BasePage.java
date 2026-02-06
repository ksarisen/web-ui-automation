package com.akakce.pages;

import com.akakce.driver.DriverManager;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected AndroidDriver driver = DriverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    protected WebElement waitAndSend(By locator, String product) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.sendKeys(product);
        return element;
    }

    protected void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
