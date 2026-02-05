package com.vakifbank.hooks;

import com.vakifbank.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @After
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                WebDriver driver = DriverManager.getDriver();

                // Alert varsa önce kapat
                try {
                    driver.switchTo().alert().accept();
                } catch (NoAlertPresentException ignored) {}

                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

                scenario.attach(screenshot, "image/png", "FAILED");

            } catch (Exception e) {
                System.out.println("Screenshot alınamadı: " + e.getMessage());
            }
        }

        DriverManager.quitDriver();
    }
}
