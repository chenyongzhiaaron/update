package com.aaron.training.cosmos;

import com.aaron.training.framework.BlueRose;

/**
 * Created by Administrator on 12/6/2017.
 */
public class login {
    private BlueRose loginc;
    public login(BlueRose loginc){this.loginc=loginc;}
    public void Loginto(){
        loginc.typeById("email","admin@crazybaby.com");
        loginc.typeById("password","123456");
        loginc.typeByXpath(".//*[@id='captcha']","1234");

        while (true){
            loginc.pause(1000);
            System.out.println("登录按钮没有消息继续点击");
            try {
                loginc.clickByXpath(".//*[@id='cosmos']/div/div/div/form/div[4]/div/div/button");
            }catch (Exception e){
                break;
            }
        }
    }
}
