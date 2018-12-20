package com.aaron.training.pageObject;

import com.aaron.training.framework.BlueRose;


/**
 * Created by Administrator on 11/5/2017.
 */
public class LoginStore {
    private BlueRose blueRose;
    public LoginStore(BlueRose blueRose){
        this.blueRose =blueRose;
    }
    public void Loginto(){
        blueRose.clickById("cart-trigger");
        blueRose.clickByClass("global-cart-login");
        blueRose.typeByXpath(".//*[@id='email-input']","__Testaaron@crazybaby.com");
        blueRose.typeById("password-input","123456");
        blueRose.typeById("captcha-input","1234");
        blueRose.clickByXpath("html/body/div[4]/div[3]/div/div[2]/div/div/form/button");
//            while (true) {
//                blueRose.pause(1000);
//                System.out.println("登陆按钮还没有消失，继续点击");
//                try {
//                    blueRose.clickByXpath("html/body/div[4]/div[3]/div/div[2]/div/div/form/button");
//                } catch (Exception e) {
//                    break;
//                }
//        }
    }
}
