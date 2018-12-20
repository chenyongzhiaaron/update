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
import java.util.concurrent.TimeUnit;


/**
 * Created by Administrator on 2016/5/12.
 */
public class TestMyPage {
    private  WebDriver driver;
    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "res/chromedriver.exe");
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("file:///E:/workSpace/seleniumTraining/page/seleniumTraining.html");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test(description = "测试我的页面")
    public void testMyPage() throws Exception {
        List<WebElement> inputList =
                driver.findElements(By.tagName("input"));
        //文本框输入值
        inputList.get(0).sendKeys("这是个文本框");
        //点击复选框
        inputList.get(1).click();
        //输入密码
        inputList.get(2).sendKeys("123456789");
        //点击单选框
        inputList.get(3).click();
        //遍历下拉列表框
        WebElement select = driver.findElement(By.tagName("select"));
        List<WebElement> allOptions = select.findElements(By.tagName("option"));
        for (WebElement option : allOptions) {
            System.out.println(String.format("Value is: %s", option.getAttribute("value")));
            option.click();
            System.out.println(driver.findElement(By.id("secondDiv")).getAttribute("style"));
            Thread.sleep(1000);
        }
        //点击按钮
        inputList.get(4).click();
        Thread.sleep(3000);
        //切换到alert
        Alert alert = driver.switchTo().alert();
        //点击确定
        alert.accept();
    }
}