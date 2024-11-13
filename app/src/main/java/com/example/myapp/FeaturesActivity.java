package com.example.myapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FeaturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        TextView featuresTextView = findViewById(R.id.featuresTextView);
        String features = "\n\n" +
                "• User-friendly interface\n" +
                "• Real-time speech recognition\n" +
                "• Text-to-speech functionality\n" +
                "• Speech-to-text functionality\n" +
                "• Dictionary functionality\n" +
                "• Offline capabilities\n";

        featuresTextView.setText(features);


    }
}
