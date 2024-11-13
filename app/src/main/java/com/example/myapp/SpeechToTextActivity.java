package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToTextActivity extends AppCompatActivity {

    private SpeechRecognizer speechRecognizer;
    private EditText outputEditText;
    private ImageButton startButton;
    private StringBuilder outputText;
    private Locale currentLocale = Locale.getDefault();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        // Initialize UI components
        outputEditText = findViewById(R.id.outputText);
        startButton = findViewById(R.id.btnMic);

        outputText = new StringBuilder();

        // Initialize Speech Recognizer and set custom listener
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new CustomRecognitionListener());

        // Start listening for speech
        startButton.setOnClickListener(v -> startListening());

        // Set OnClickListener for the back button

    }

    private void startListening() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, currentLocale);
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        speechRecognizer.startListening(intent);
    }

    // Custom listener for real-time speech recognition
    private class CustomRecognitionListener implements RecognitionListener {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(SpeechToTextActivity.this, "Listening...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {
            outputText.setLength(0); // Clear previous text
        }

        @Override
        public void onRmsChanged(float rmsdB) {}

        @Override
        public void onBufferReceived(byte[] buffer) {}

        @Override
        public void onEndOfSpeech() {
            Toast.makeText(SpeechToTextActivity.this, "Processing...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(int error) {
            String errorMessage = getErrorText(error);
            Toast.makeText(SpeechToTextActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if (matches != null && !matches.isEmpty()) {
                outputText.append(matches.get(0)).append(" ");
                outputEditText.setText(outputText.toString());
            }
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            ArrayList<String> partial = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if (partial != null && !partial.isEmpty()) {
                outputEditText.setText(partial.get(0)); // Show live speech
            }
        }

        @Override
        public void onEvent(int eventType, Bundle params) {}

        // Utility method to provide error descriptions
        private String getErrorText(int errorCode) {
            switch (errorCode) {
                case SpeechRecognizer.ERROR_AUDIO: return "Audio recording error";
                case SpeechRecognizer.ERROR_CLIENT: return "Client-side error";
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS: return "Insufficient permissions";
                case SpeechRecognizer.ERROR_NETWORK: return "Network error";
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT: return "Network timeout";
                case SpeechRecognizer.ERROR_NO_MATCH: return "No recognition result matched";
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY: return "Recognition service busy";
                case SpeechRecognizer.ERROR_SERVER: return "Error from server";
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT: return "No speech input";
                default: return "Recognition error";
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}
