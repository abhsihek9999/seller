package com.hk.seller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.hk.seller.core.database.AppPref;
import com.hk.seller.utils.AppSignatureHelper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppSignatureHelper appSignatureHelper = new AppSignatureHelper(SplashActivity.this);
        Log.v("HASH", appSignatureHelper.getAppSignatures().get(0));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppPref.INSTANCE.getModelInstance().getLoggedInMode()) {

                } else {
                    Intent intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                    startActivity(intent);
                }
            }
        }, 2000);


    }
}
