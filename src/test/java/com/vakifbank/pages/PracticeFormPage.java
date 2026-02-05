package com.vakifbank.pages;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PracticeFormPage extends BasePage {

    // URL
    private final String URL = "https://demoqa.com/elements";

    // Locators
    private final By formsHeader = By.xpath("//div[@class='header-text' and text()='Forms']");
    private final By practiceFormItem = By.xpath("//span[text()='Practice Form']");
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("userEmail");

    private final By genderMaleRadio = By.xpath("//label[text()='Male']");
    private final By mobileNumberInput = By.id("userNumber");

    private final By dateOfBirthInput = By.id("dateOfBirthInput");
    private final By monthSelect = By.className("react-datepicker__month-select");
    private final By yearSelect = By.className("react-datepicker__year-select");

    private final By subjectsInput = By.id("subjectsInput");

    private final By hobbySports = By.xpath("//label[text()='Sports']");
    private final By hobbyReading = By.xpath("//label[text()='Reading']");
    private final By hobbyMusic = By.xpath("//label[text()='Music']");

    private final By uploadPictureInput = By.id("uploadPicture");

    private final By currentAddressInput = By.id("currentAddress");

    private final By stateDropdown = By.id("react-select-3-input");
    private final By cityDropdown = By.id("react-select-4-input");

    private final By submitButton = By.id("submit");

    private final By modalTableRows = By.cssSelector(".table-responsive tbody tr");

    // Modal (Thanks for submitting the form)
    private final By modalTitle = By.id("example-modal-sizes-title-lg");

    // Actions
    public void navigateToPracticeForm() {
        driver.get(URL);
        
        // Forms kategorisini aç
        jsClick(formsHeader);

        // Practice Form'a gir
        jsClick(practiceFormItem);
    }

    public void fillMainFields(String firstName, String lastName, String email, String mobile) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(emailInput, email);
        click(genderMaleRadio);
        type(mobileNumberInput, mobile);
    }

    public void fillMandatoryFieldsOnly(String firstName, String lastName, String mobile) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        click(genderMaleRadio);
        type(mobileNumberInput, mobile);
    }

    public void submitForm() {
        scrollIntoView(submitButton);
        removeFixedFooter();
        jsClick(submitButton);
    }

    public String getModalTitle() {
        return getText(modalTitle);
    }

    public void selectDateOfBirth(LocalDate date) {
        jsClick(dateOfBirthInput);

        Select year = new Select(waitForVisibility(yearSelect));
        year.selectByVisibleText(String.valueOf(date.getYear()));

        Select month = new Select(waitForVisibility(monthSelect));
        month.selectByIndex(date.getMonthValue() - 1);

        By day = By.xpath(
            "//div[contains(@class,'react-datepicker__day') and text()='" + date.getDayOfMonth() +
            "' and not(contains(@class,'react-datepicker__day--outside-month'))]"
        );
        click(day);
    }

    public void fillSubjects(String subject) {
        type(subjectsInput, subject);
        driver.findElement(subjectsInput).sendKeys(Keys.ENTER);
    }

    public void selectRandomHobby() {
        List<By> hobbies = List.of(hobbySports, hobbyReading, hobbyMusic);
        By selected = hobbies.get(new Random().nextInt(hobbies.size()));
        jsClick(selected);
    }

    public void uploadPicture() {
        try {
            Path filePath = Paths.get(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResource("test-image.png")
                ).toURI()
            );

            WebElement upload = wait.until(ExpectedConditions.presenceOfElementLocated(uploadPictureInput));

            upload.sendKeys(filePath.toString());

        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    public void fillCurrentAddress(String address) {
        type(currentAddressInput, address);
    }

    public void selectState(String state) {
        driver.findElement(stateDropdown).sendKeys(state);
        driver.findElement(stateDropdown).sendKeys(Keys.ENTER);
    }
    public void selectCity(String city) {
        driver.findElement(cityDropdown).sendKeys(city);
        driver.findElement(cityDropdown).sendKeys(Keys.ENTER);
    }
    
    public Map<String, String> getSubmittedFormData() {
        Map<String, String> data = new HashMap<>();

        List<WebElement> rows = driver.findElements(modalTableRows);
        for (WebElement row : rows) {
            String key = row.findElements(By.tagName("td")).get(0).getText();
            String value = row.findElements(By.tagName("td")).get(1).getText();
            data.put(key, value);
        }
        return data;
    }
}
