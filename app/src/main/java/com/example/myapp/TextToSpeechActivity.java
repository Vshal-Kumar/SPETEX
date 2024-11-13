package com.example.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity {

    private TextToSpeech tts;
    private EditText inputText;
    private Button btnPlay, btnDownload;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        inputText = findViewById(R.id.inputText);
        btnPlay = findViewById(R.id.btnPlay);
        btnDownload = findViewById(R.id.btnDownload);

        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(Locale.US);
            }
        });

        btnPlay.setOnClickListener(v -> {
            String text = inputText.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        });

        btnDownload.setOnClickListener(v -> {
            // Check for permission to write to external storage
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        WRITE_EXTERNAL_STORAGE_REQUEST);
            } else {
                saveAudioToFile();
            }
        });

        // Set OnClickListener for the back button

    }

    private void saveAudioToFile() {
        String text = inputText.getText().toString();
        // Ensure text is not empty before synthesizing
        if (text.isEmpty()) {
            Toast.makeText(this, "Please enter text to convert to audio", Toast.LENGTH_SHORT).show();
            return;
        }

        // Define the file path for saving audio
        File audioFile = new File(Environment.getExternalStorageDirectory(), "downloaded_audio.wav");

        // Use the TextToSpeech to synthesize to a file
        int result = tts.synthesizeToFile(text, null, audioFile, "tts");

        if (result == TextToSpeech.SUCCESS) {
            Toast.makeText(this, "Audio downloaded to: " + audioFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to download audio", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveAudioToFile();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
