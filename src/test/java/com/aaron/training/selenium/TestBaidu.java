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
public class TestBaidu {
    private  WebDriver driver;
    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.ie.driver", "res/chromedriver.exe");
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.baidu.com");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test(description = "测试百度搜索")
    public void testBaiduSerach() throws Exception {
        WebElement searchInput=driver.findElement(By.id("kw"));
        searchInput.sendKeys("java");
        WebElement searchaButton=driver.findElement(By.id("su"));
        searchaButton.click();
//        Thread.sleep(5000);
//        WebElement clickButten = driver.findElement(By.linkText("登录"));
    }
}