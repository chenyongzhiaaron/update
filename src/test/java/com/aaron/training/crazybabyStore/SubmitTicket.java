package com.aaron.training.crazybabyStore;

import com.aaron.training.datadriver.ExcelDataProvider;
import com.aaron.training.framework.BlueRose;
import com.aaron.training.pageObject.LoginStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 11/5/2017.
 */
public class SubmitTicket {
    private static BlueRose ticket;
    @BeforeClass
    public void setUp() throws Exception{

    }
    @AfterClass
    public void tearDown() throws Exception{
        ticket.quit();
    }
    @AfterMethod
    public void afterMethod()throws Exception{
        ticket.quit();
    }


    @Test(description = "浏览器的数据驱动实现",dataProvider = "browserType")
    public void submitTicket(Map<String,String> data) throws Exception{
        System.out.print("current browser type is :"+data.get("type"));
        ticket = new BlueRose(Integer.parseInt(data.get("type").trim()));
        ticket.openUrl("https://test.crazybaby.com/");
        LoginStore login = new LoginStore(ticket);
        login.Loginto();
        Thread.sleep(2000);
//        登录成功点击 support 按钮
        ticket.clickByXpath("html/body/footer/div/div[2]/div[2]/ul/li[4]/a");
        Thread.sleep(1500);
//        点击 AIR 图标进入搜 faq 页面
        ticket.clickByXpath("html/body/div[4]/section[2]/div[1]/a");
//        输入搜索内容
        ticket.typeById("coupon","HOW");
//        点击 右侧问题分类 Warranty
        ticket.clickByXpath("html/body/div[4]/section[3]/div[2]/div/div/span/button");
//        断言搜索结果
        ticket.expectExistOrNotByXpath(true,"html/body/div[4]/section[3]/div[3]/div[1]/ul/li[37]/a/span[1]");

//        点击 提交工单按钮进入工单页面
        ticket.clickByXpath("html/body/div[4]/section[3]/div[3]/div[3]/div[3]/div/div/div/div[1]/a");
        ticket.typeByName("subject","TEST！！！这是一个自动化TEST TICKET ！！");
        ticket.clickByName("product");
//        WebElement select = ticket.findByClass("select");
//        List<WebElement> allOptions = select.findElements(By.className("dropdown-menu"));
//        for (WebElement option:allOptions){
//            System.out.println(String.format("value is : %s",option.getAttribute("href")));

//        }


    }


    @DataProvider(name = "browserType")
    public Iterator<Object[]> dataFortestMethod(Method method)throws IOException{
        return new ExcelDataProvider(this.getClass().getName(),method.getName());
    }

}
