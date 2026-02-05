package com.vakifbank.stepdefinitions;

import com.vakifbank.pages.BookStorePage;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.testng.Assert;

import java.util.List;

public class BookStoreSteps {

    private BookStorePage bookStorePage = new BookStorePage();
    private List<String> books;
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("user navigates to Book Store page")
    public void user_navigates_to_book_store_page() {
        bookStorePage.navigateToBookStore();
    }

    @Then("book table rows should be displayed correctly")
    public void book_table_rows_should_be_displayed_correctly() {

        books = bookStorePage.getAllBooksAsList();

        Assert.assertTrue(books.size() > 0, "Book table has no rows");
    }

    @Then("book details should be listed in the report")
    public void book_details_should_be_listed_in_the_report() {

        scenario.log("Total book count: " + books.size());
        scenario.log("Book List:");

        for (String book : books) {
            scenario.log(book);
        }
    }
}
