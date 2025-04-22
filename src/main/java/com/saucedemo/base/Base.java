package com.saucedemo.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Base {
    public static WebDriver driver;
    public static Properties prop = new Properties();

    public Base() throws IOException {
        try {
            FileInputStream input = new FileInputStream(new File("src/main/java/com/saucedemo/properities/data.properities"));
            prop.load(input);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void start() {
        if(prop.getProperty("browser").equalsIgnoreCase("chrome")){
            final Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

            final ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOptions);
        }
        else if(prop.getProperty("browser").equalsIgnoreCase("Fire")){
            driver = new FirefoxDriver();
        }
        else {
            System.out.println("Web Driver is invalid.");
            return;
        }
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
    }
}
