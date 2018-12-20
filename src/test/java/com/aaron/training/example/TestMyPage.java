package com.aaron.training.example;

import com.aaron.training.framework.BlueRose;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;


/**
 * Created by Administrator on 2016/5/12.
 */
public class TestMyPage {
    private static BlueRose blueRose;

    @BeforeClass
    public void setUp() throws Exception {
        blueRose = new BlueRose(4);
//        blueRose = new BlueRose(1,"http://localhost:4444/wd/hub");
        blueRose.openUrl("file:///E:/workSpace/seleniumTraining/page/seleniumTraining.html");
    }

    @AfterClass
    public void tearDown() throws Exception {
        blueRose.quit();
    }

    @Test(description = "测试我的页面")
    @Parameters({"userName","passWord"})
    public void testMyPage(String userName,String passWord) throws Exception {
        System.out.println("userName is:"+userName);
        System.out.println("passWord is:"+passWord);
        //文本框输入值
//        inputList.get(0).sendKeys("这是个文本框");
        blueRose.executeJs("var a=document.getElementById('firstDiv');var b=a.children;b[0].value='asdasda';");
        //点击复选框
//        inputList.get(1).click();

        blueRose.executeJs("var a=document.getElementById('firstDiv');var b=a.children;b[2].checked=true;");
        //输入密码
        blueRose.typeByTagNameAndIndex("input",2,"123456789");
        //点击单选框
        blueRose.clickByTagNameAndIndex("input",3);
        //遍历下拉列表框
        WebElement select = blueRose.findByTag("select");
        List<WebElement> allOptions = select.findElements(By.tagName("option"));
        for (WebElement option : allOptions) {
            System.out.println(String.format("Value is: %s", option.getAttribute("value")));
            option.click();
            blueRose.pause(1000);
        }
        //点击按钮
//        blueRose.clickByName("confirmme");
        WebElement webElement = blueRose.getDriver().findElement(By.name("confirmme"));
        blueRose.executeJs("arguments[0].click();", webElement);
//        WebElement button=blueRose.findByJs("return document.getElementById('clickme')");
//        button.click();
//        blueRose.pause(2000);
        //切换到alert
        blueRose.alertConfirm();


//        var a=document.getElementById('firstDiv');var b=a.children;b[0].value='asdasda';

    }
}
