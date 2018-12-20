package com.aaron.training.pageObject;

import com.aaron.training.framework.BlueRose;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by gavin on 16/5/21.
 */
public class Login {

    private BlueRose blueRose;

    public Login(BlueRose blueRose) {
        this.blueRose = blueRose;
    }

    public void longinto() {
        blueRose.mouseClickAndHoldById("js_N_navHighlight");
        WebElement iframe = blueRose.findByJs("return document.getElementById('js_N_login_wrap')");
        String iframeId = iframe.findElement(By.tagName("iframe")).getAttribute("id");
        blueRose.enterFrameById(iframeId);
        blueRose.typeByName("email", "gavintraining@163.com");
        blueRose.typeByName("password", "gavin888");
        while (true) {
            blueRose.pause(1000);
            System.out.println("登陆按钮还没有消失，继续点击");
            try {
                blueRose.clickById("dologin");
            } catch (Exception e) {

                break;
            }
        }
        blueRose.leaveFrame();
    }
}
