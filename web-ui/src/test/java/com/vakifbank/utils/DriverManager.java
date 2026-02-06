package com.vakifbank.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.LocalFileDetector;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final String GRID_URL = "http://selenium-hub:4444/wd/hub";

    private DriverManager() {
        // prevent instantiation
    }

    /* ---------- DRIVER CREATE ---------- */
    public static WebDriver getDriver() {

        if (driver.get() == null) {

            String browser = System.getProperty("browser", "chrome");

            try {
                URL gridUrl = URI.create(GRID_URL).toURL();
                RemoteWebDriver remoteDriver;

                switch (browser.toLowerCase()) {

                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        remoteDriver = new RemoteWebDriver(gridUrl, edgeOptions);
                        break;

                    case "chrome":
                    default:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        remoteDriver = new RemoteWebDriver(gridUrl, chromeOptions);
                        break;
                }

                // GRID FILE UPLOAD İÇİN ŞART
                remoteDriver.setFileDetector(new LocalFileDetector());

                driver.set(remoteDriver);

            } catch (MalformedURLException e) {
                throw new RuntimeException("Grid URL is invalid: " + GRID_URL, e);
            }
        }

        return driver.get();
    }

    /* ---------- DRIVER QUIT ---------- */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
