package com.aaron.training.crazybabyStore;

import com.aaron.training.datadriver.ExcelDataProvider;
import com.aaron.training.framework.BlueRose;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 20/10/2017.
 */
public class TestLogin {
    private BlueRose loginCase;
    @BeforeClass
    private void setUp()throws Exception{

    }
    @AfterClass
    private void tearDown()throws Exception{
        loginCase.quit();
    }
    @AfterMethod
    private void afterMethon()throws Exception{
        loginCase.quit();
    }
    @Test(description = "数据驱动",dataProvider = "browserType")
    private void testLogin(Map<String,String>data)throws Exception{
        System.out.println("Current browser type is:"+ data.get("type") );
        loginCase = new BlueRose(Integer.parseInt(data.get("type").trim()));

    }

    @DataProvider(name = "browserType")
    public Iterator<Object[]>dataForMethod(Method method)throws Exception{
        return new ExcelDataProvider(this.getClass().getName(),method.getName());
    }

}
