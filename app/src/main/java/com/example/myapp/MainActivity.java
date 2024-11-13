package com.example.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnTextToSpeech, btnSpeechToText, btnDictionary;
    private static final int PERMISSION_REQUEST_CODE = 101;
    private static final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this layout is correctly defined

        // Initialize buttons
        btnTextToSpeech = findViewById(R.id.btnTextToSpeech);
        btnSpeechToText = findViewById(R.id.btnSpeechToText);
        btnDictionary = findViewById(R.id.btnDictionary);

        // Check and request permissions
        if (checkPermissions()) {
            initializeButtons();
        } else {
            requestPermissions();
        }
    }

    // Initialize button actions after permission check
    private void initializeButtons() {
        btnTextToSpeech.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TextToSpeechActivity.class)));
        btnSpeechToText.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SpeechToTextActivity.class)));
        btnDictionary.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, DictionaryActivity.class)));
    }

    // Check if permissions are granted
    private boolean checkPermissions() {
        for (String permission : PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    // Request permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    // Handle the result of permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length == PERMISSIONS.length) {
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                initializeButtons();
            }  else {
                Toast.makeText(this, "Permissions denied. Some features may not work.", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    // Handle menu item selections
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_f) {
            startActivity(new Intent(MainActivity.this, FeaturesActivity.class));
            return true;
        } else if (id == R.id.about_us) {
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
