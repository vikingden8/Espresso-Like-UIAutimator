package com.viking.support.test.jyn;

import android.content.Intent;
import android.os.RemoteException;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.view.KeyEvent;

import java.io.IOException;

import static android.support.test.InstrumentationRegistry.getContext;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import static android.support.test.uiautomator.Until.hasObject;

/**
 * Author : Viking Den<dengwj@gionee.com>
 * Time : 4/5/17 3:33 PM
 */

public final class DeviceInteraction {

    private final UiDevice mDevice ;

    DeviceInteraction(UiDevice mDevice){
        this.mDevice = mDevice ;
    }

    /**
     * Presses the home button and waits for the launcher with a timeout of 5 seconds.
     *
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction onHomeScreen() {
        return onHomeScreen(5000);
    }

    /**
     * Presses the home button and waits for the launcher with the given timeout.
     *
     * @param timeout length of time in milliseconds to wait for the launcher to appear before
     *        timing out.
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction onHomeScreen(long timeout) {
        mDevice.pressHome();

        //judge that we really on home screen
        String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(hasObject(By.pkg(launcherPackage).depth(0)), timeout);

        return this;
    }

    /**
     * Asserts that the foreground app has the given package name. Waits for up to 5 seconds for the
     * given package to become the foreground app.
     *
     * @param packageName package name to check against the foreground app.
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction checkForegroundAppIs(String packageName) {
        return checkForegroundAppIs(packageName, 5000);
    }

    /**
     * Asserts that the foreground app has the given package name. Waits for up to the given timeout
     * for the given package to become the foreground app.
     *
     * @param packageName package name to check against the foreground app.
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction checkForegroundAppIs(String packageName, long timeout) {
        mDevice.wait(hasObject(By.pkg(packageName).depth(0)), timeout);
        assertTrue(mDevice.hasObject(By.pkg(packageName).depth(0)));
        return this;
    }

    /**
     * Launches the app with the given package name and waits for it to start with a timeout of 5 seconds.
     *
     * @param packageName package name of the app to launch.
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction launchApp(String packageName) {
        return launchApp(packageName, 5000);
    }

    /**
     * Launches the app with the given package name and waits for it to start with the given timeout.
     *
     * @param packageName package name of the app to launch.
     * @param timeout length of time in milliseconds to wait for the app to appear before timing out.
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction launchApp(String packageName, long timeout) {
        return launchApp(getContext().getPackageManager().getLaunchIntentForPackage(packageName), timeout);
    }

    /**
     * Launches the intent and waits for it to start with a timeout of 5 seconds.
     *
     * @param intent {@link Intent} to launch
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction launchApp(Intent intent) {
        return launchApp(intent, 5000);
    }

    /**
     * Launches the intent and waits for it to start with the given timeout.
     *
     * @param intent {@link Intent} to launch
     * @param timeout length of time in milliseconds to wait for the app to appear before timing out.
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction launchApp(Intent intent, long timeout) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(intent);

        mDevice.wait(hasObject(By.pkg(intent.getPackage()).depth(0)), timeout);

        return this;
    }

    /**
     * Simulates a short press on the BACK button.
     *
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction pressBack() {
        mDevice.pressBack();
        return this;
    }

    /**
     * Simulates a short press on the MENU button.
     *
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction pressMenu() {
        mDevice.pressMenu();
        return this;
    }

    /**
     * Simulates a short press on the Recent Apps button.
     *
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction pressRecentApps() {
        try {
            mDevice.pressRecentApps();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /**
     * Simulates a short press on the SEARCH button.
     *
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction pressSearch() {
        mDevice.pressSearch();
        return this;
    }

    /**
     * Simulates a short press on the ENTER key.
     *
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction pressEnter() {
        mDevice.pressEnter();
        return this;
    }

    //remove pressDPadXXX methods

    /**
     * Opens the notification shade.
     *
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction openNotification() {
        mDevice.openNotification();
        return this;
    }

    /**
     * Opens the Quick Settings shade.
     *
     * @return {@link DeviceInteraction} for method chaining.
     */
    public DeviceInteraction openQuickSettings() {
        mDevice.openQuickSettings();
        return this;
    }

    /**
     * Simulates a short press using a key code.
     *
     * See {@link KeyEvent}
     *
     * @return true if successful, else return false
     */
    public boolean pressKeyCode(int keycode){
        return mDevice.pressKeyCode(keycode) ;
    }

    /**
     * Perform a click at arbitrary coordinates specified by the user
     *
     * @param x coordinate
     * @param y coordinate
     * @return true if the click succeeded else false
     */
    public boolean click(int x, int y){
        return mDevice.click(x, y) ;
    }

    /**
     * Performs a swipe from one coordinate to another coordinate. You can control
     * the smoothness and speed of the swipe by specifying the number of steps.
     * Each step execution is throttled to 5 milliseconds per step, so for a 100
     * steps, the swipe will take around 0.5 seconds to complete.
     *
     * @param startX X-axis value for the starting coordinate
     * @param startY Y-axis value for the starting coordinate
     * @param endX X-axis value for the ending coordinate
     * @param endY Y-axis value for the ending coordinate
     * @param steps is the number of steps for the swipe action
     *
     * @return false if the operation fails or the coordinates are invalid
     */
    public boolean drag(int startX, int startY, int endX, int endY, int steps){
        return mDevice.drag(startX, startY, endX, endY, steps) ;
    }

    /**
     * Performs a swipe from one coordinate to another using the number of steps
     * to determine smoothness and speed. Each step execution is throttled to 5ms
     * per step. So for a 100 steps, the swipe will take about 1/2 second to complete.
     *
     * @param startX X-axis value for the starting coordinate
     * @param startY Y-axis value for the starting coordinate
     * @param endX X-axis value for the ending coordinate
     * @param endY Y-axis value for the ending coordinate
     * @param steps is the number of steps for the swipe action
     *
     * @return false if the operation fails or the coordinates are invalid
     */
    public boolean swipe(int startX, int startY, int endX, int endY, int steps){
        return mDevice.swipe(startX, startY, endX, endY, steps) ;
    }

    /**
     * Executes a shell command using shell user identity, and return the standard output in string.
     * <p>
     * Calling function with large amount of output will have memory impacts, and the function call
     * will block if the command executed is blocking.
     * <p>Note: calling this function requires API level 21 or above
     * @param cmd the command to run
     *
     */
    public void executeShellCommand(String cmd){
        try {
            mDevice.executeShellCommand(cmd) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enables or disables layout hierarchy compression.
     *
     * If compression is enabled, the layout hierarchy derived from the Acessibility
     * framework will only contain nodes that are important for uiautomator
     * testing. Any unnecessary surrounding layout nodes that make viewing
     * and searching the hierarchy inefficient are removed.
     *
     * @param compressed true to enable compression; else, false to disable
     *
     * return {@link DeviceInteraction} for method chaining.
     */
    public void setCompressedLayoutHeirarchy(boolean compressed){
        mDevice.setCompressedLayoutHeirarchy(compressed);
    }

    /**
     * Checks the power manager if the screen is ON.
     *
     * @return {@code true} if the screen is ON else {@code false}
     */
    public boolean isScreenOn() {
        try {
            return mDevice.isScreenOn();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
