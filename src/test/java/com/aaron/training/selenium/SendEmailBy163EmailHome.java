package com.aaron.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by Administrator on 2016/5/12.
 */
public class SendEmailBy163EmailHome {

    private static WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        //selenium webdriver local
        System.setProperty("webdriver.chrome.driver", "res/chromedriver.exe");
        driver = new ChromeDriver();
        //selenium grid remote control
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
//        driver.manage().window().maximize();
        driver.get("http://mail.163.com/");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test(description = "登陆163邮箱发邮件")
    public void sendMail() throws Exception {
        //输入用户名
        driver.switchTo().frame(driver.findElement(By.id("x-URS-iframe")));
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("seleniumtraining");
        //输入密码
        driver.findElement(By.name("password")).sendKeys("training888");
        //点击登录按钮
        driver.findElement(By.id("dologin")).click();
        //点击写邮件
        WebElement sendMail = (new WebDriverWait(driver, 30)).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.xpath("/html/body/div[1]/nav/div[1]/ul/li[2]/span[2]"));
//                        return d.findElement(By.id(" "));
                    }
                }
        );
        sendMail.click();
        //输入收件人邮箱地址
        driver.findElement(By.className("nui-editableAddr-ipt")).sendKeys("gavintraining@163.com");
        //输入邮件主题
        driver.findElement(By.xpath("" +
                "/html/body/div[2]/div[1]/div[2]/div[1]/section/header/div[2]/div[1]/div/div/input")).sendKeys("selenium training email");
        //切换到邮件内容的iframe
        driver.switchTo().frame(driver.findElement(By.className("APP-editor-iframe")));

        Thread.sleep(3000);
        //输入邮件内容
        driver.findElement(By.className("nui-scroll")).sendKeys("please tell me content");
        //返回到默认iframe
        driver.switchTo().defaultContent();
        //点击发送邮件
        WebElement submitMail = (new WebDriverWait(driver, 30)).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.className("nui-toolbar-item"));
                    }
                }
        );
        submitMail.click();
        Thread.sleep(3000);
        //验证发送结果
        WebElement result = (new WebDriverWait(driver, 30)).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/section/h1"));
                    }
                }
        );
        Assert.assertEquals(result.isDisplayed(), true);
    }
}
