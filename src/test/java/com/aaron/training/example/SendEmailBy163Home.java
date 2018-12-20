package com.aaron.training.example;

import com.aaron.training.framework.BlueRose;
import com.aaron.training.pageObject.Login;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Created by Administrator on 2016/5/12.
 */
public class SendEmailBy163Home {

    private BlueRose blueRose;

    @BeforeClass
    public void setUp() throws Exception {
        blueRose = new BlueRose(3);
//        blueRose = new BlueRose(1,"http://127.0.0.1:4444/wd/hub");
        blueRose.openUrl("http://www.163.com/");
    }

    @AfterClass
    public void tearDown() throws Exception {
//        blueRose.quit();
    }

    @Test(description = "登陆163邮箱发邮件")
    public void sendMail() throws Exception {
        Login login = new Login(blueRose);
        login.longinto();
        blueRose.mouseClickAndHoldById("js_N_logined_warp");
        blueRose.clickByLinkText("我的邮箱");
        blueRose.switchWindow();
        //点击发送邮件
        blueRose.clickByXpath("/html/body/div[1]/nav/div[1]/ul/li[2]/span[2]");
        //输入收件人地址
        blueRose.typeByClass("nui-editableAddr-ipt", "seleniumtraining@163.com");
        //输入邮件主题
        blueRose.typeByXpath("/html/body/div[2]/div[1]/div[2]/div[1]/section/header/div[2]/div[1]/div/div/input", "selenium training email");
        blueRose.typeByClassAndIndex("nui-ipt-input",2,"主题");
        //切换到邮件内容的iframe
        blueRose.enterFramebyClass("APP-editor-iframe");
        //输入邮件内容
        blueRose.typeByClass("nui-scroll", "please tell me content");
        //返回到默认的frame
        blueRose.leaveFrame();
        //点击发送邮件
        blueRose.clickByClass("nui-toolbar-item");
        //等待3秒
        blueRose.pause(3000);
        blueRose.expectExistOrNotByXpath(true, "/html/body/div[2]/div[1]/div[2]/div[2]/section/h1");
    }
}
