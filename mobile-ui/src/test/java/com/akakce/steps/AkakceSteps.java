package com.akakce.steps;

import com.akakce.hooks.Hooks;
import com.akakce.pages.AkakcePage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class AkakceSteps {

    AkakcePage page = Hooks.page;
    String listPrice;

    @Given("user is on Akakce home page")
    public void user_is_on_akakce_home_page() {
        page = new AkakcePage();
    }

    @Given("user searches for Laptop")
    public void searchLaptop() {
        page.searchProduct("Laptop");
    }

    @When("user applies filters")
    public void applyFilters() {
        page.openFilter();

        page.searchFilter("Bluetooth");
        page.selectFilterResult("Bluetooth");

        page.searchFilter("Laptop");
        page.selectFilterResult("Laptop / Notebook");

        page.searchFilter("5.3");
        page.selectFilterResult("5.3");
        
        page.applyFilters();
    }

    @And("user sorts by lowest price")
    public void sort() {
        page.sortByLowestPrice();
    }

    @And("user selects {int}th product")
    public void selectProduct(int index) {
        page.selectProductByIndex(index-1);
        listPrice = page.getListPrice();
    }

    @Then("prices should be equal and seller button visible")
    public void validate() {
        page.goToProduct();
        String detailPrice = page.getDetailPrice();

        Assert.assertEquals(
                normalize(listPrice),
                normalize(detailPrice),
                "Prices are not equal"
        );

        Assert.assertTrue(page.isGoToSellerVisible());
    }

    @Then("intentionally fail the scenario")
    public void intentionally_fail_the_scenario() {
        Assert.fail("Intentional failure to verify screenshot capture");
    }

    private double normalize(String price) {
        return Double.parseDouble(
                price.replace(".", "")
                        .replace(",", ".")
                        .replaceAll("[^0-9.]", "")
        );
    }
}
