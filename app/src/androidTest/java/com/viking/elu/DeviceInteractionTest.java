package com.viking.elu;

import android.content.Intent;
import android.provider.Settings;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.view.KeyEvent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.viking.support.test.jyn.Jyn.* ;
import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Author : Viking Den<dengwj@gionee.com>
 * Time : 4/5/17 2:25 PM
 */
@RunWith(AndroidJUnit4.class)
public class DeviceInteractionTest {


    @Test
    public void onHomeScreen(){
        onDevice().onHomeScreen() ;
    }

    @Test
    public void onHomeScreenWithTimeout(){
        onDevice().onHomeScreen(3000) ;
    }

    @Test
    public void onFGIs(){
        onDevice().onHomeScreen().checkForegroundAppIs(UiDevice.getInstance(getInstrumentation()).getLauncherPackageName()) ;
    }

    @Test
    public void onFGIsWithTimeout(){
        onDevice().onHomeScreen().checkForegroundAppIs(UiDevice.getInstance(getInstrumentation()).getLauncherPackageName() , 3000) ;
    }


    @Test
    public void launchApp(){
        onDevice().launchApp("com.android.settings") ;
    }

    @Test
    public void launchAppWithTimeout(){
        onDevice().launchApp("com.android.settings" , 8000) ;
    }

    @Test
    public void launchAppIntent(){
        Intent intent = new Intent(Settings.ACTION_SETTINGS) ;
        intent.setPackage("com.android.settings") ;
        onDevice().launchApp(intent) ;
    }

    @Test
    public void launchAppIntentWithTimeout(){
        Intent intent = new Intent(Settings.ACTION_SETTINGS) ;
        intent.setPackage("com.android.settings") ;
        onDevice().launchApp(intent , 8000) ;
    }

    @Test
    public void pressBack(){
        onDevice().pressBack() ;
    }

    @Test
    public void pressMenu(){
        onDevice().pressMenu() ;
    }

    @Test
    public void pressRecentApps(){
        onDevice().pressRecentApps() ;
    }

    @Test
    public void pressSearch(){
        onDevice().pressSearch() ;
    }

    @Test
    public void pressEnter(){
        onDevice().pressEnter() ;
    }

    @Test
    public void openNotification(){
        onDevice().openNotification().pressBack() ;
    }

    @Test
    public void openQuickSettings(){
        onDevice().openQuickSettings().pressBack().pressBack() ;
    }

    @Test
    public void pressKeyCode(){
        onDevice().pressKeyCode(KeyEvent.KEYCODE_CAMERA) ;
        onDevice().pressBack() ;
    }

    @Test
    public void click(){
        onDevice().click(300 , 200) ;
    }

    @Test
    public void drag(){
        onDevice().drag(200, 200 , 500, 500 , 30) ;
    }


    @Test
    public void swipe(){
        onDevice().swipe(200, 200 , 500, 500 , 30) ;
    }


    @Test
    public void executeShellCommand(){
        onDevice().executeShellCommand("pm list packages");
    }


    @Test
    public void setCompressedLayoutHeirarchy(){
        onDevice().setCompressedLayoutHeirarchy(false);
    }

    @Test
    public void isScreenOn(){
        Assert.assertTrue(onDevice().isScreenOn());
    }


}
