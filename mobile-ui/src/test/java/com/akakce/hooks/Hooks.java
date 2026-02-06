package com.akakce.hooks;

import com.akakce.driver.DriverManager;
import com.akakce.pages.AkakcePage;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    public static AkakcePage page;

    @Before
    public void setUp() {
        DriverManager.getDriver();   // driver ayağa kalkar
        page = new AkakcePage();     // page hazır
        System.out.println("DRIVER AYAGA KALKTI");
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            byte[] screenshot =
                    ((TakesScreenshot) DriverManager.getDriver())
                            .getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", "FAILED SCREENSHOT");
        }

        DriverManager.quitDriver();
    }
}
