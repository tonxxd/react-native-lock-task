
package com.reactlibrary;

import android.app.Activity;
import android.view.WindowManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNLockTaskModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNLockTaskModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNLockTask";
  }

  @ReactMethod
  public void startLockTask() {
    Activity mActivity = reactContext.getCurrentActivity();

    DevicePolicyManager myDevicePolicyManager = (DevicePolicyManager) mActivity.getSystemService(Context.DEVICE_POLICY_SERVICE);
    ComponentName mDPM = new ComponentName(mActivity, MyAdmin.class);

    if (myDevicePolicyManager.isDeviceOwnerApp(mActivity.getPackageName())) {
        String[] packages = {mActivity.getPackageName()};
        myDevicePolicyManager.setLockTaskPackages(mDPM, packages);
        mActivity.startLockTask();
    } else {
        mActivity.startLockTask();
    }
  }

  @ReactMethod
  public  void stopLockTask() {
    Activity mActivity = reactContext.getCurrentActivity();;
    mActivity.stopLockTask();
  }
}
