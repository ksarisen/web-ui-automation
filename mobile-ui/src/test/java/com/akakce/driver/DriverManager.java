package com.akakce.driver;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;

public class DriverManager {

    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        if (driver.get() == null) {
            try {
                DesiredCapabilities caps = new DesiredCapabilities();

                caps.setCapability("platformName", "Android");
                caps.setCapability("automationName", "UiAutomator2");
                caps.setCapability("deviceName", "emulator-5554");
                caps.setCapability("appPackage", "com.akakce.akakce");
                caps.setCapability("appActivity",
                        "com.akakce.akakce.ui.main.mainactivity.MainActivity");
                caps.setCapability("noReset", true);
                caps.setCapability("newCommandTimeout", 300);

                AndroidDriver androidDriver = new AndroidDriver(
                        URI.create("http://127.0.0.1:4723").toURL(),
                        caps
                );

                driver.set(androidDriver);

            } catch (Exception e) {
                throw new RuntimeException("Driver init failed", e);
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
