package com.vakifbank.stepdefinitions;

import com.vakifbank.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class InvalidNavigationSteps {

    @Given("user navigates to Elements page")
    public void user_navigates_to_elements_page() {
        DriverManager.getDriver().get("https://demoqa.com/elements");
    }

    @When("user tries to click a non-existing menu item")
    public void user_tries_to_click_non_existing_menu_item() {
        try {
            DriverManager.getDriver().findElement(By.xpath("//span[text()='Deneme']")).click();
            Assert.fail("Element should not exist");
        } catch (NoSuchElementException e) {
            // expected behaviour
        }
    }

    @Then("operation should fail gracefully")
    public void operation_should_fail_gracefully() {
        // Asıl validation screenshot + exception handling ile yapıldı
    }
}
