package com.example.myapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);



        // Set up the TextView to display the About Us information
        TextView aboutUsTextView = findViewById(R.id.aboutUsTextView);
        aboutUsTextView.setText(
                "<h2><b style='font-size:26sp;'>App Overview</b></h2>" +
                        "<strong><b style='font-size:20sp;'>Name:</b></strong> Spetex<br>" +
                        "<strong><b style='font-size:20sp;'>Developers:</b></strong> Vishal Kumar and Harsha Chakoley<br>" +
                        "<strong><b style='font-size:20sp;'>Mission:</b></strong> To enhance communication through innovative technology.<br><br>" +

                        "<h2><b style='font-size:26sp;'>About the App</b></h2>" +
                        "Spetex is designed to be a user-friendly application aimed at making language and information more accessible to everyone. " +
                        "The application provides various features that empower users in their daily interactions and learning experiences.<br><br>" +

                        "<h2><b style='font-size:26sp;'>Future Updates</b></h2>" +
                        "<p style='font-size:18sp;'>As part of our ongoing commitment to enhance the app, we are excited to announce future updates that will introduce:</p>" +
                        "<ul>" +
                        "<li style='font-size:20sp;'><b>Multi-Language Support:</b> Spetex will soon support multiple languages, ensuring that users from all over the world can enjoy seamless communication and interaction within the app.</li>" +
                        "<li style='font-size:20sp;'><b>Language Translation:</b> A new translation feature will be introduced to break down language barriers and allow users to communicate effortlessly across different languages. This feature will allow you to translate text and speech in real-time, ensuring a smoother experience for international users.</li>" +
                        "</ul>" +
                        "<p style='font-size:20sp;'>Stay tuned for these exciting updates and more, as we continue to improve Spetex to meet your communication needs.</p><br><br>"
        );
        aboutUsTextView.setText(Html.fromHtml(aboutUsTextView.getText().toString(), Html.FROM_HTML_MODE_LEGACY));
    }
}
