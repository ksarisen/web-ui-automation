package com.vakifbank.stepdefinitions;

import com.vakifbank.pages.AlertsPage;
import com.vakifbank.utils.DriverManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import java.time.Duration;

public class AlertsSteps {

    private AlertsPage alertsPage = new AlertsPage();

    /* -------------------- GIVEN -------------------- */

    @Given("user navigates to Alerts page")
    public void user_navigates_to_alerts_page() {
        alertsPage.navigateToAlertsPage();
    }

    /* -------------------- WHEN -------------------- */

    @When("user clicks all alert buttons")
    public void user_clicks_all_alert_buttons() {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(6));

        /* ---------- SIMPLE ALERT ---------- */
        alertsPage.clickSimpleAlert();

        Alert simpleAlert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(simpleAlert.getText(), "You clicked a button");

        simpleAlert.accept();

        /* ---------- TIMER ALERT ---------- */
        alertsPage.clickTimerAlert();

        Alert timerAlert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(
                timerAlert.getText().contains("5 seconds"),
                "Timer alert text is incorrect"
        );
        timerAlert.accept();

        /* ---------- CONFIRM ALERT ---------- */
        alertsPage.clickConfirmAlertDismiss();

        /* ---------- PROMPT ALERT ---------- */
        alertsPage.clickPromptAlert();
        Alert promptAlert = wait.until(ExpectedConditions.alertIsPresent());

        promptAlert.sendKeys("Kerem");
        promptAlert.accept();
    }

    /* -------------------- THEN -------------------- */

    @Then("alert messages should be validated correctly")
    public void alert_messages_should_be_validated_correctly() {
        
        alertsPage.validateConfirmResult("You selected Cancel");
        alertsPage.validatePromptResult("Kerem");
    }
}
