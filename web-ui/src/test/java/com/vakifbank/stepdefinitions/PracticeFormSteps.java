package com.vakifbank.stepdefinitions;

import com.vakifbank.pages.PracticeFormPage;

import com.vakifbank.utils.TestDataGenerator;
import com.vakifbank.utils.DateUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PracticeFormSteps {

    private PracticeFormPage practiceFormPage;
    private LocalDate randomDob;
    private Map<String, String> expectedData = new HashMap<>();

     /* -------------------- GIVEN -------------------- */

    @Given("user navigates to Practice Form page")
    public void user_navigates_to_practice_form_page() {
        practiceFormPage = new PracticeFormPage();
        practiceFormPage.navigateToPracticeForm();
    }

    /* -------------------- WHEN (ALL FIELDS) -------------------- */

    @When("user fills all fields with valid data")
    public void user_fills_all_fields_with_valid_data() {

        String firstName = TestDataGenerator.randomFirstName();
        String lastName = TestDataGenerator.randomLastName();
        String email = TestDataGenerator.randomEmail();
        String mobile = "5551234567";
        String subject = TestDataGenerator.randomSubject();
        String address = "Istanbul Besiktas Test Address";

        randomDob = TestDataGenerator.randomDateOfBirth();

        practiceFormPage.fillMainFields(firstName, lastName, email, mobile);
        practiceFormPage.selectDateOfBirth(randomDob);
        practiceFormPage.fillSubjects(subject);
        practiceFormPage.selectRandomHobby();
        practiceFormPage.uploadPicture();
        practiceFormPage.fillCurrentAddress(address);
        practiceFormPage.selectState("NCR");
        practiceFormPage.selectCity("Delhi");

        expectedData.put("Student Name", firstName + " " + lastName);
        expectedData.put("Student Email", email);
        expectedData.put("Gender", "Male");
        expectedData.put("Mobile", mobile);
        expectedData.put("Date of Birth", DateUtils.formatDob(randomDob));
        expectedData.put("Subjects", subject);
        expectedData.put("Picture", "test-image.png");
        expectedData.put("Address", address);
        expectedData.put("State and City", "NCR Delhi");
    }

    /* -------------------- WHEN (MANDATORY ONLY) -------------------- */

    @When("user fills only mandatory fields")
    public void user_fills_only_mandatory_fields() {

        String firstName = TestDataGenerator.randomFirstName();
        String lastName = TestDataGenerator.randomLastName();
        String mobile = "5551234567";

        practiceFormPage.fillMandatoryFieldsOnly(firstName, lastName, mobile);

        expectedData.clear();
        expectedData.put("Student Name", firstName + " " + lastName);
        expectedData.put("Gender", "Male");
        expectedData.put("Mobile", mobile);
    }

    /* -------------------- WHEN (SUBMIT) -------------------- */

    @When("user submits the form")
    public void user_submits_the_form() {
        practiceFormPage.submitForm();
    }

    /* -------------------- THEN (ALL FIELDS) -------------------- */

    @Then("all submitted form data should be displayed correctly")
    public void all_fields_should_be_displayed_correctly() {

        // 1️⃣ Modal açıldı mı?
        Assert.assertEquals(
                practiceFormPage.getModalTitle(),
                "Thanks for submitting the form",
                "Submission modal did not open"
        );

        // 2️⃣ Modal tablo validation
        Map<String, String> actualData = practiceFormPage.getSubmittedFormData();

        for (String key : expectedData.keySet()) {
            Assert.assertEquals(
                    actualData.get(key),
                    expectedData.get(key),
                    key + " value mismatch"
            );
        }
    }

    /* -------------------- THEN (MANDATORY ONLY) -------------------- */

    @Then("only mandatory form data should be displayed correctly")
    public void only_mandatory_fields_should_be_displayed_correctly() {

        Assert.assertEquals(practiceFormPage.getModalTitle(), "Thanks for submitting the form");

        Map<String, String> actualData =
                practiceFormPage.getSubmittedFormData();

        // Mandatory fields
        Assert.assertEquals(actualData.get("Student Name"), expectedData.get("Student Name"));
        Assert.assertEquals(actualData.get("Gender"), expectedData.get("Gender"));
        Assert.assertEquals(actualData.get("Mobile"), expectedData.get("Mobile"));

        // Optional fields should be empty
        Assert.assertTrue(actualData.get("Subjects").isEmpty());
        Assert.assertTrue(actualData.get("Hobbies").isEmpty());
        Assert.assertTrue(actualData.get("Picture").isEmpty());
        Assert.assertTrue(actualData.get("Address").isEmpty());
    }
}
