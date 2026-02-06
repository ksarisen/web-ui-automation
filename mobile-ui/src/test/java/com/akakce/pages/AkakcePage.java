package com.akakce.pages;

import com.akakce.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AkakcePage extends BasePage {

    private final AndroidDriver driver = DriverManager.getDriver();

    /* ---------- SEARCH ---------- */

    private final By searchBar = AppiumBy.id("com.akakce.akakce:id/searchTextView");

    public void searchProduct(String product) {

        // click (fresh locate)
        waitAndClick(searchBar);

        // sendKeys (fresh locate AGAIN)
        waitAndSend(searchBar, product);

        // Android SEARCH tuşu
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    /* ---------- FILTER ---------- */
    By filterSearchInput = AppiumBy.id("com.akakce.akakce:id/searchEdt");

    public void openFilter() {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().resourceId(\"com.akakce.akakce:id/filterArea\"))"
                )
        ).click();
    }

    public void searchFilter(String text) {
        WebElement search = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(filterSearchInput));

        search.clear();
        search.sendKeys(text);
    }

    public void selectFilterResult(String filterText) {

        By filterResult = AppiumBy.androidUIAutomator(
                "new UiSelector()" +
                ".resourceId(\"com.akakce.akakce:id/cloudBox\")" +
                ".childSelector(new UiSelector().text(\"" + filterText + "\"))"
        );

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(filterResult));
        element.click();

        // Filter seçilince klavye kapanmalı, garanti altına al
        driver.hideKeyboard();
    }

    public void applyFilters() {
        By button = AppiumBy.id("com.akakce.akakce:id/apply");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    /* ---------- SORT ---------- */

    public void sortByLowestPrice() {
        driver.findElement(AppiumBy.id("com.akakce.akakce:id/sortArea")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.akakce.akakce:id/sort_cell_bg\").instance(1)")).click();
    }

    /* ---------- PRODUCT ---------- */

    public void selectProductByIndex(int targetIndex) {

        By cardLocator = AppiumBy.id("com.akakce.akakce:id/card");
        By nameLocator = AppiumBy.id("com.akakce.akakce:id/name");

        Set<String> seenProductNames = new LinkedHashSet<>();

        int maxScroll = 10;
        int scrollCount = 0;

        while (scrollCount < maxScroll) {
                
                List<WebElement> cards = driver.findElements(cardLocator);

                for (WebElement card : cards) {

                        List<WebElement> names = card.findElements(nameLocator);

                        // Reklam / banner → skip
                        if (names.isEmpty()) continue;

                        String productName = names.get(0).getText().trim();

                        // daha önce saydıysak geç
                        if (!seenProductNames.add(productName)) continue;

                        // hedef index (0-based)
                        if (seenProductNames.size() - 1 == targetIndex) {
                                card.click();
                                return;
                        }
                }
                // kontrollü scroll (SADECE productRecycler)
                driver.findElement(
                        AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector()" +
                                ".resourceId(\"com.akakce.akakce:id/productRecycler\"))" +
                                ".scrollForward()"
                        )
                );
                try {
                        Thread.sleep(800);
                } catch (InterruptedException ignored) {}

                scrollCount++;
        }
        throw new RuntimeException("Product index " + targetIndex + " not found after scrolling");
    }

    public String getListPrice() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.akakce.akakce:id/price\")")).getText();
    }

    public void goToProduct() {
        driver.findElement(AppiumBy.id("com.akakce.akakce:id/detailBtnLayout")).click();
    }

    /* ---------- DETAIL PRICE (ROBUST) ---------- */

    public String getDetailPrice() {

        // Ekrandaki tüm TextView’ları al
        List<WebElement> texts =
                driver.findElements(By.className("android.widget.TextView"));

        for (int i = 0; i < texts.size() - 1; i++) {

                String first = texts.get(i).getText().trim();
                String second = texts.get(i + 1).getText().trim();

                // Örn: "21,460" + ",33"
                if (first.matches("\\d{1,3}([.,]\\d{3})*") && second.matches(",\\d{2}")) {
                        return first + second; // 21,460,33
                }
        }

        throw new RuntimeException("Detail price not found");
    }

    public boolean isGoToSellerVisible() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Satıcıya Git\")")).isDisplayed();
    }
}
