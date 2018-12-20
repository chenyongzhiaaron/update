package com.aaron.training.crazybabyStore;

import com.aaron.training.datadriver.ExcelDataProvider;
import com.aaron.training.framework.BlueRose;
import com.aaron.training.pageObject.LoginStore;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 17/10/2017.
 */
public class EditPersonalInformation {
    private static BlueRose edit;
    @BeforeClass
    public void setUp()throws Exception{

    }
    @AfterClass
    public void tearDown()throws Exception{

    }
    @AfterMethod
    public void afterMethod()throws Exception{
//        edit.quit();
    }
    @Test(description = "浏览器数据驱动实现",dataProvider = "browserType")
    private void editInformation(Map<String,String>data)throws Exception{
        System.out.println("current browser is:"+ data.get("type"));
        edit = new BlueRose(Integer.parseInt(data.get("type").trim()));
       edit.openUrl("https://test.crazybaby.com/");
        LoginStore login = new LoginStore(edit);
        login.Loginto();
        Thread.sleep(2000);
        edit.clickById("cart-trigger"); // 点击购物车图标
        edit.clickByClass("global-cart-login"); // 点击购物车内登录按钮
        edit.clickByXpath("html/body/div[4]/div[3]/section[2]/div[1]/a"); //  点击编辑个人信息
        edit.typeByName("name","__Test-1234@crazybaby.***"); // 输入姓名
        edit.typeByName("email","aaron@crazybaby.com"); // 输入 邮箱
        edit.clickByName("birthday"); // 点击生日控件
        edit.clickByXpath(".//*[@id='datepickers-container']/div/nav/div[2]");// 选择年份
        edit.clickByXpath(".//*[@id='datepickers-container']/div/div/div[2]/div/div[4]"); // 选择月份
        edit.clickByXpath(".//*[@id='datepickers-container']/div/div/div[1]/div[2]/div[30]"); // 选择日
        edit.clickByXpath(".//*[@id='gender-dropdown']/button"); // 点击
        edit.clickByXpath(".//*[@id='gender-dropdown']/ul/li[1]/a");
        edit.typeByName("phone_number","020-79898999"); // 输入手机号码
        edit.typeByName("new_password","ankani#12");// 输入密码
        edit.typeByName("new_password_confirmation","ankani#12"); // 重新输入密码
        edit.typeByName("password","123456"); // 输入当前密码
        edit.clickByXpath("html/body/div[4]/div[3]/div[2]/div/div[2]/form[2]/button"); // 点击提交按钮
        edit.expectExistOrNotByXpath(true,"html/body/div[4]/div[3]/div[2]/div/div[1]/strong"); //断言修改成功
    }
    @DataProvider(name = "browserType")
    private Iterator<Object[]> dataFortestMethod(Method method)throws IOException{
        return new ExcelDataProvider(this.getClass().getName(),method.getName());
    }
}
