package com.vakifbank.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

public class AlertsPage extends BasePage {
    // URL
    private final String URL = "https://demoqa.com/elements";

    // Locators 
    private final By alertsMenu = By.xpath("//span[text()='Alerts']");
    private final By alertsHeader = By.xpath("//div[@class='header-text' and text()='Alerts, Frame & Windows']");

    private final By simpleAlertBtn = By.id("alertButton");
    private final By timerAlertBtn = By.id("timerAlertButton");
    private final By confirmAlertBtn = By.id("confirmButton");
    private final By promptAlertBtn = By.id("promtButton");

    private By confirmResultText = By.id("confirmResult");
    private By promptResultText  = By.id("promptResult");

    // Actions
    public void navigateToAlertsPage() {
        driver.get(URL);

        jsClick(alertsHeader);
        jsClick(alertsMenu);
    }

    public void clickSimpleAlert() {
        click(simpleAlertBtn);
    }

    public void clickTimerAlert() {
        click(timerAlertBtn);
    }

    public void clickConfirmAlertAccept() {
        driver.findElement(confirmAlertBtn).click();
        driver.switchTo().alert().accept();
    }
    
    public void clickConfirmAlertDismiss() {
        driver.findElement(confirmAlertBtn).click();
        driver.switchTo().alert().dismiss();
    }

    public void clickPromptAlert() {
        click(promptAlertBtn);
    }

    // CONFIRM ALERT RESULT VALIDATION
    public void validateConfirmResult(String expected) {
        String actualText = getText(confirmResultText);
        Assert.assertEquals(
                actualText,
                expected,
                "Confirm alert result text mismatch"
        );
    }

    // PROMPT ALERT RESULT VALIDATION
    public void validatePromptResult(String expected) {
        String actualText = getText(promptResultText);
        Assert.assertEquals(
                actualText,
                "You entered " + expected,
                "Prompt alert result text mismatch"
        );
    }
}
