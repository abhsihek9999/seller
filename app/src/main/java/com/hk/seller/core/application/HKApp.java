package com.hk.seller.core.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.hk.seller.core.application.di.component.AppComponent;
import com.hk.seller.core.application.di.component.DaggerAppComponent;
import com.hk.seller.core.application.di.module.AppModule;
import com.hk.seller.core.application.di.module.UtilsModule;

public class HKApp extends Application {

    private AppComponent appComponent;
    public static HKApp context;
    public static Activity activtyContext;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .utilsModule(new UtilsModule())
                .build();

        appComponent.injectAppContext(this);


    }

    public AppComponent getAppComponent() {

        return appComponent;
    }

    public static HKApp  getAppContext() {

        return context;
    }

    public static void setActivityContext(Activity context) {

        activtyContext = context;
    }


    public static Activity getActivityContext() {

        return activtyContext;
    }


    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
