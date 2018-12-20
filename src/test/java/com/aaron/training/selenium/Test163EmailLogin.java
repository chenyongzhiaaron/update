package com.aaron.training.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


/**
 * Created by Administrator on 2016/5/12.
 */
public class Test163EmailLogin {
    private WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "res/chromedriver.exe");
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://mail.163.com");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test(description = "测试我的页面")
    public void testMyPage() throws Exception {
        WebElement iframe=driver.findElement(By.id("x-URS-iframe"));
        driver.switchTo().frame(iframe);
        Thread.sleep(10000);
        WebElement userName=driver.findElement(By.name("email"));
        userName.sendKeys("seleniumtraining");
        WebElement passWord=driver.findElement(By.name("password"));
        passWord.sendKeys("training888");
        WebElement login=driver.findElement(By.id("dologin"));
        login.click();
        Thread.sleep(50000);
    }
}