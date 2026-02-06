package com.vakifbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class BookStorePage extends BasePage {

    private final String URL = "https://demoqa.com/elements";

    // LEFT MENU
    private final By bookStoreApp = By.xpath("//div[@class='header-text' and text()='Book Store Application']");
    private final By bookStoreMenu = By.xpath("//span[text()='Book Store']");

    // TABLE
    private final By bookRows = By.cssSelector(".rt-tbody .rt-tr-group");
    private final By title = By.cssSelector(".rt-td:nth-child(2)");
    private final By author = By.cssSelector(".rt-td:nth-child(3)");
    private final By publisher = By.cssSelector(".rt-td:nth-child(4)");

    public void navigateToBookStore() {
        driver.get(URL);

        // doğru parent menüyü aç
        jsClick(bookStoreApp);

        // Book Store'a gir
        jsClick(bookStoreMenu);

        // tablo yüklenene kadar bekle
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bookRows));
    }

    public List<String> getAllBooksAsList() {

        List<String> books = new ArrayList<>();
        List<WebElement> rows = driver.findElements(bookRows);

        for (int i = 0; i < rows.size(); i++) {

            String bookTitle = rows.get(i).findElement(title).getText();
            String bookAuthor = rows.get(i).findElement(author).getText();
            String bookPublisher = rows.get(i).findElement(publisher).getText();

            books.add(
                (i + 1) + ". " +
                bookTitle + " | " +
                bookAuthor + " | " +
                bookPublisher
            );
        }
        return books;
    }
}
