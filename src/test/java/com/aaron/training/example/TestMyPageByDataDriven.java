package com.aaron.training.example;

import com.aaron.training.datadriver.ExcelDataProvider;
import com.aaron.training.framework.BlueRose;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/5/12.
 */
public class TestMyPageByDataDriven {
    private static BlueRose blueRose;

    @BeforeClass
    public void setUp() throws Exception {
    }

    @AfterClass
    public void tearDown() throws Exception {
//        blueRose.quit();
    }

    @AfterMethod
    public void afterMethod() throws Exception{
//        blueRose.quit();
    }

    @Test(description = "浏览器的数据驱动实现",dataProvider = "browserType")
    public void testMyPage(Map<String, String> data) throws Exception {
        System.out.print("current browser type is:"+data.get("type"));
        blueRose = new BlueRose(Integer.parseInt(data.get("type").trim()));
//        blueRose = new BlueRose(Integer.parseInt(data.get("type").trim()),"http://127.0.0.1:4444/wd/hub");
        blueRose.openUrl("file:///F:/seleniumTraining/page/seleniumTraining.html");
        //文本框输入值
        blueRose.typeByTagNameAndIndex("input",0,"这是个文本框");
        //点击复选框
        blueRose.clickByTagNameAndIndex("input",1);
        //输入密码
        blueRose.typeByTagNameAndIndex("input",2,"123456789");
        //点击单选框
        blueRose.clickByTagNameAndIndex("input",3);
        //遍历下拉列表框
        WebElement select = blueRose.findByTag("select");
        List<WebElement> allOptions = select.findElements(By.tagName("select"));
        for (WebElement o : allOptions) {
            System.out.println(String.format("Value is: %s", o.getAttribute("value")));
            o.click();
            blueRose.pause(1000);
        }
        //点击按钮
        blueRose.clickById("clickme");
        blueRose.pause(2000);
        //切换到alert
        blueRose.alertConfirm();
    }

    @DataProvider(name = "browserType")
    public Iterator<Object[]> dataFortestMethod(Method method) throws IOException {
        return new ExcelDataProvider(this.getClass().getName(), method.getName());
    }
}
