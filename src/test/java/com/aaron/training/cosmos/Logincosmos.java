package com.aaron.training.cosmos;

import com.aaron.training.datadriver.ExcelDataProvider;
import com.aaron.training.framework.BlueRose;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 12/6/2017.
 */
public class Logincosmos {
    private static BlueRose blueRose;
    @BeforeClass
    public void setUp()throws Exception{

    }
    @AfterClass
    public void tearDowm()throws Exception{
        blueRose.quit();
    }
    @AfterMethod
    public void afertMethod(){
        blueRose.quit();
    }
    @Test(description = "数据驱动登录",dataProvider = "browserType")
    public void logincosmos(Map<String,String>data)throws Exception{


    }
    @DataProvider(name = "browserType")
    public Iterator<Object[]>dataFortestMethod(Method method)throws IOException{
      return new ExcelDataProvider(this.getClass().getName(),method.getName());
    }
}
