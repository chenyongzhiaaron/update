package com.aaron.training.selenium;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2016/5/12.
 */
public class BaiduSearch {
    private WebDriver driver;
    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "res/chromedriver.exe");
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.baidu.com/");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test(description = "测试我的页面")
    public void testMyPage() throws Exception {
        WebElement input=driver.findElement(By.id("kw"));
        Thread.sleep(2000);
        input.sendKeys("你好");
        Thread.sleep(2000);
        WebElement button=driver.findElement(By.id("su"));
        button.click();
        Thread.sleep(2000);
    }
}