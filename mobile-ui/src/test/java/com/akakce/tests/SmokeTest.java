package com.akakce.tests;

import com.akakce.driver.DriverManager;
import org.testng.annotations.Test;

public class SmokeTest {

    @Test
    public void openAppTest() {
        DriverManager.getDriver();
        System.out.println("DRIVER AYAĞA KALKTI");
    }
}
