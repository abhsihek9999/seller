package com.hk.seller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


import com.hk.seller.login.presentation.view.LoginActivity;
import com.hk.seller.utils.onBoard.PaperOnboardingEngine;
import com.hk.seller.utils.onBoard.PaperOnboardingOnChangeListener;
import com.hk.seller.utils.onBoard.PaperOnboardingOnRightOutListener;
import com.hk.seller.utils.onBoard.PaperOnboardingPage;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class OnBoardingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(R.id.onboardingRootView), getDataForOnboarding(), getApplicationContext());

        engine.setOnChangeListener(new PaperOnboardingOnChangeListener() {
            @Override
            public void onPageChanged(int oldElementIndex, int newElementIndex) {
            }
        });

        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                // Probably here will be your exit action
            }
        });

    }

    // Just example data for Onboarding
    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        // prepare data
        PaperOnboardingPage scr1 = new PaperOnboardingPage(getResources().getString(R.string.onboardtextone), getResources().getString(R.string.onboardsubtextone),
                Color.parseColor("#ffffff"), R.drawable.testbreakfast, R.drawable.onboard_selected);
        PaperOnboardingPage scr2 = new PaperOnboardingPage(getResources().getString(R.string.onboardtexttwo), getResources().getString(R.string.onboardsubtextone),
                Color.parseColor("#ffffff"), R.drawable.testbreakfast, R.drawable.onboard_selected);
        PaperOnboardingPage scr3 = new PaperOnboardingPage(getResources().getString(R.string.onboardtextthree), getResources().getString(R.string.onboardsubtextone),
                Color.parseColor("#ffffff"), R.drawable.testbreakfast, R.drawable.onboard_selected);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
