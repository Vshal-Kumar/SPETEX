package com.example.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "myPrefs";
    private static final String ONBOARDING_COMPLETE_KEY = "onboardingComplete";

    private ViewPager2 viewPager;
    private List<Integer> onboardingScreens;
    private View[] dotIndicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean onboardingComplete = prefs.getBoolean(ONBOARDING_COMPLETE_KEY, false);
        Log.d("OnboardingActivity", "Onboarding completed. Starting MainActivity.");
        if (onboardingComplete) {
            startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        onboardingScreens = new ArrayList<>();
        onboardingScreens.add(R.layout.onboarding_screen_1);
        onboardingScreens.add(R.layout.onboarding_screen_2);
        onboardingScreens.add(R.layout.onboarding_screen_3);
        Log.d("OnboardingActivity", "Adapter set for ViewPager.");


        dotIndicators = new View[]{
                findViewById(R.id.dot1),
                findViewById(R.id.dot2),
                findViewById(R.id.dot3)
        };

        if (dotIndicators.length != onboardingScreens.size()) {
            throw new IllegalStateException("The number of dot indicators must match the number of onboarding screens.");
        }

        OnboardingAdapter adapter = new OnboardingAdapter(onboardingScreens, this);
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateDotIndicators(position);

                if (position == onboardingScreens.size() - 1) {
                    findViewById(R.id.btnNext).setVisibility(View.GONE);
                    findViewById(R.id.btnSkip).setVisibility(View.GONE);
                    findViewById(R.id.btnFinish).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.btnNext).setVisibility(View.VISIBLE);
                    findViewById(R.id.btnSkip).setVisibility(View.VISIBLE);
                    findViewById(R.id.btnFinish).setVisibility(View.GONE);
                }
            }
        });

        findViewById(R.id.btnNext).setOnClickListener(view -> {
            if (viewPager.getCurrentItem() < onboardingScreens.size() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

            }
        });

        findViewById(R.id.btnSkip).setOnClickListener(view -> completeOnboarding());
        Log.d("OnboardingActivity", "Next button clicked.");
        findViewById(R.id.btnFinish).setOnClickListener(view -> completeOnboarding());
        Log.d("OnboardingActivity", "Skip or Finish button clicked.");

    }

    private void updateDotIndicators(int position) {
        for (int i = 0; i < dotIndicators.length; i++) {
            dotIndicators[i].setBackgroundResource(i == position ? R.drawable.active_circle : R.drawable.circle);
            Log.d("OnboardingActivity", "Current page: " + position);

        }
    }

    private void completeOnboarding() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().putBoolean(ONBOARDING_COMPLETE_KEY, true).apply();
        Log.d("OnboardingActivity", "Onboarding completed. Navigating to MainActivity.");
        startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
        finish();
    }

}
