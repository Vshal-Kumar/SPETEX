package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY =2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Ensure you have an XML layout named activity_splash

        // Delay to simulate loading time (adjusted to 1 second)
        new Handler().postDelayed(() -> {
            SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
            boolean onboardingComplete = prefs.getBoolean("onboardingComplete", false);
            Log.d("SplashActivity", "Onboarding complete: " + onboardingComplete);


            // Log onboarding completion status for debugging
            Log.d("SplashActivity", "Onboarding complete: " + onboardingComplete);

            // Redirect based on onboarding status
            if (onboardingComplete) {
                // If onboarding is complete, go to MainActivity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                // If onboarding is not complete, go to OnboardingActivity
                startActivity(new Intent(SplashActivity.this, OnboardingActivity.class));
            }
            finish(); // Finish SplashActivity
        }, SPLASH_DELAY); // Reduced to 1000ms (1 second)
    }
}
