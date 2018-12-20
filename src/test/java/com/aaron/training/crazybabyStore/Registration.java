package com.aaron.training.crazybabyStore;

import com.aaron.training.datadriver.ExcelDataProvider;
import com.aaron.training.framework.BlueRose;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 18/10/2017.
 */
public class Registration {
    private static BlueRose regist;
    @BeforeClass
    private void setUp()throws Exception{

    }
    @AfterClass
    private void tearDown()throws Exception{
//        regist.quit();
    }
    @AfterMethod
    private void afterMethod()throws Exception{

    }
    @Test(description = "数据驱动的测试",dataProvider = "browserType")
    private void testRegist(Map<String,String>data)throws Exception{
        System.out.println("current window is " + data.get("type"));
        regist = new BlueRose(Integer.parseInt(data.get("type").trim()));
        regist.openUrl(data.get("URL"));
        regist.clickById("cart-trigger");
        regist.clickByClass("global-cart-login");
        regist.clickByLinkText("New registration");
        regist.typeById("name",data.get("name"));
        regist.typeById("email",data.get("email"));
        regist.typeById("password",data.get("pwd"));
        regist.typeById("password-confirm",data.get("newpwd"));
        regist.typeById("captcha-input","1234");
        regist.clickByXpath("html/body/div[4]/div[3]/div/div/div/div/form/button");
        regist.expectExistOrNotByXpath(true,"html/body/div[4]/div[3]/div/div/div/div/form/div[2]/div/strong");

    }
    @DataProvider(name = "browserType")
    public Iterator<Object[]>dataForTestMethod(Method method)throws IOException{
        return new ExcelDataProvider(this.getClass().getName(),method.getName());
    }



}
