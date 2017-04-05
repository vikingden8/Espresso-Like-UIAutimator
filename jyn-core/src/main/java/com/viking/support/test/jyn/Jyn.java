package com.viking.support.test.jyn;

import android.support.test.uiautomator.UiDevice;

/**
 * Author : Viking Den<dengwj@gionee.com>
 * Time : 4/5/17 3:27 PM
 *
 * Entry point to the Jyn framework. Test authors can initiate testing by using one of the on*
 * methods (e.g. onView or onDevice) or perform top-level user actions (e.g. pressBack or pressHome).
 */

public final class Jyn {

    private UiDevice mDevice ;

    //let construct private
    private Jyn(){}

    public static DeviceInteraction withDevice() {
        return new DeviceInteraction() ;
    }

}
