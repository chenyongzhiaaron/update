package com.aaron.training.crazybabyStore;

import com.aaron.training.datadriver.ExcelDataProvider;
import com.aaron.training.framework.BlueRose;
import com.aaron.training.pageObject.EnterInformationAddress;
import com.aaron.training.pageObject.LoginStore;

import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 22/5/2017.
 */
public class LoginShopping {
    private static BlueRose shopping;
    @BeforeClass
    public void setUp()throws Exception{}
    @AfterClass
    public void tearDowm()throws Exception{}
    @AfterMethod
    public void afterMethod()throws Exception{
//        shopping.quit();
    }
    @Test(description = "浏览器的数据驱动实现",dataProvider = "browserType")
//    @Parameters({"userName","passWord"})
    private void testLoginshopping(Map<String,String>data) throws Exception{
        System.out.println("current browser is:"+ data.get("type"));
        shopping= new BlueRose(Integer.parseInt(data.get("type").trim()));
        shopping.openUrl("https://test.crazybaby.com");
        LoginStore login =new LoginStore(shopping);
        login.Loginto();
        Thread.sleep(3000);
//        点击 Mars 购物车
        shopping.clickByXpath("/html/body/div[8]/div[1]/div/div[2]/div/div[1]/a[2]");
//        选择颜色为灰色
//        shopping.clickByClass("white");
//        点击加入购物车
        shopping.clickByXpath("html/body/div[6]/div[3]/button");
//        输入产品数量

        shopping.typeByXpath(".//*[@id='cart-content']/div[1]/div[1]/div/div[3]/div[1]/div[2]/input","9");
        shopping.clickByLinkText("CHECKOUT");

        EnterInformationAddress enter = new EnterInformationAddress(shopping);
        enter.EnterAddress();
//       输入信用卡信息
        Thread.sleep(2000);
        shopping.clickByName("payment_method");
        shopping.typeById("number","4242424242424242");
        shopping.clickByXpath(".//*[@id='credit-card-form']/div[2]/div/div[1]/input");
        shopping.clickByXpath(".//*[@id='datepickers-container']/div/div/div/div/div[9]");
        shopping.clickByXpath(".//*[@id='datepickers-container']/div/div/div[2]/div/div[12]");
        shopping.typeById("cvc","123");
        shopping.clickById("stripe-button");
        shopping.expectExistOrNotByXpath(true,"html/body/div[4]/div[2]/div/h2");

    }

    @DataProvider(name = "browserType")
    public Iterator<Object[]> dataFortestMethod(Method method)throws IOException{
        return new ExcelDataProvider(this.getClass().getName(),method.getName());
    }
}
