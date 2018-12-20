package com.aaron.training.pageObject;

import com.aaron.training.framework.BlueRose;

/**
 * Created by Administrator on 16/10/2017.
 */
public class EnterInformationAddress {
    private BlueRose blueRose;
    public EnterInformationAddress(BlueRose blueRose) {
        this.blueRose = blueRose;
    }
    public void EnterAddress() {
        blueRose.typeByName("recipient", "crazybaby_陈勇志—Test");
        blueRose.typeByName("recipient", "18127813600");
        blueRose.typeByName("address", "蛇口网谷万海大夏B坐405_Test");
        blueRose.typeByName("city", "广东Test-深圳");
        blueRose.typeByName("state", "广东Test-深圳");
        blueRose.typeByName("zip_code", "518110Test");
        blueRose.clickByXpath("html/body/div[5]/div[3]/div/div/form/button");
//        blueRose.clickByClass("btn btn-primary btn-wide btn-lg");
    }

}