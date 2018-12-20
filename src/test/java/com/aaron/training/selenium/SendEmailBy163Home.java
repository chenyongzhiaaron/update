package com.aaron.training.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by Administrator on 2016/5/12.
 */
public class SendEmailBy163Home {

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
        driver.get("http://www.163.com/");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test(description = "登陆163邮箱发邮件")
    public void sendMail() throws Exception {
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //鼠标归位到原点
        rb.mouseMove(0, 0);
        WebElement loginBefore = driver.findElement(By.id("js_N_navHighlight"));
        Actions action = new Actions(driver);
        //鼠标悬停到登录按钮上
        action.moveToElement(loginBefore).clickAndHold().build().perform();
        //切换到输入用户名密码的iframe
//        driver.switchTo().frame(driver.findElement(By.id("x-URS-iframe")));
//        //输入用户名
//        driver.findElement(By.name("email")).clear();
//        driver.findElement(By.name("email")).sendKeys("gavintraining@163.com");
//        //输入密码
//        driver.findElement(By.name("password")).sendKeys("gavin888");
//        WebElement iframe = blueRose.findByJs("return document.getElementById('js_N_login_wrap')");
        WebElement iframe = (WebElement) ((JavascriptExecutor) driver).executeScript("return document.getElementById('js_N_login_wrap')");
        String iframeId = iframe.findElement(By.tagName("iframe")).getAttribute("id");
        driver.switchTo().frame(driver.findElement(By.id(iframeId)));
        driver.findElement(By.name("email")).sendKeys("gavintraining@163.com");
        driver.findElement(By.name("password")).sendKeys("gavin888");
        WebElement dologin = driver.findElement(By.id("dologin"));
        //点击登录按钮
        dologin.click();
        while (true) {
            Thread.sleep(1000);
            System.out.println("登陆按钮还没有消失，继续点击");
            try {
                dologin.click();
            } catch (Exception e) {
                break;
            }
        }

        driver.switchTo().defaultContent();
        //进入邮箱
        rb.mouseMove(0, 0);
        WebElement loginAfter = (new WebDriverWait(driver, 30)).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.id("js_N_logined_warp"));
                    }
                }
        );
        action.moveToElement(loginAfter).clickAndHold().build().perform();
        WebElement myEmail = (new WebDriverWait(driver, 30)).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.linkText("我的邮箱"));
                    }
                }
        );
        myEmail.click();
        Thread.sleep(5000);
        //进入新打开的邮箱窗口
        String currentWindow = driver.getWindowHandle();//获取当前窗口句柄
        Set<String> handles = driver.getWindowHandles();//获取所有窗口句柄
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            if (currentWindow == it.next()) {
                continue;
            }
            driver.switchTo().window(it.next());//切换到新窗口
        }
        WebElement sendMail = (new WebDriverWait(driver, 30)).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.xpath("/html/body/div[1]/nav/div[1]/ul/li[2]/span[2]"));
                    }
                }
        );
        //点击发送邮件
        sendMail.click();
        //输入收件人
        driver.findElement(By.className("nui-editableAddr-ipt")).sendKeys("seleniumtraining@163.com");
        //输入邮件主题
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/section/header/div[2]/div[1]/div/div/input")).sendKeys("selenium training email");
        //切换到邮件内容的iframe
        driver.switchTo().frame(driver.findElement(By.className("APP-editor-iframe")));
        //输入邮件内容
        driver.findElement(By.className("nui-scroll")).sendKeys("please tell me content");
        //返回到默认的frame
        driver.switchTo().defaultContent();
        WebElement submitMail = (new WebDriverWait(driver, 30)).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.className("nui-toolbar-item"));
                    }
                }
        );
        //点击发送邮件
        submitMail.click();
        Thread.sleep(3000);
        WebElement result = (new WebDriverWait(driver, 30)).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {


                        return driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/section/h1"));
                    }
                }
        );
        //验证邮件是否发送成功
        Assert.assertEquals(result.isDisplayed(), true);
    }
}
