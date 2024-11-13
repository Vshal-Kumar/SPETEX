package com.example.myapp;

import android.os.Bundle; // Import Bundle class
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.ArrayList;

public class CustomRecognitionListener implements RecognitionListener {

    private final StringBuilder outputText;

    public CustomRecognitionListener(StringBuilder outputText) {
        this.outputText = outputText;
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Log.d("SpeechToText", "Ready for speech");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.d("SpeechToText", "Speech has begun");
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        // You can handle changes in volume here if needed
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        // Buffer received
    }

    @Override
    public void onEndOfSpeech() {
        Log.d("SpeechToText", "Speech has ended");
    }

    @Override
    public void onError(int error) {
        Log.e("SpeechToText", "Error: " + error);
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (matches != null && !matches.isEmpty()) {
            // Append the recognized text to outputText
            outputText.append(matches.get(0));
            Log.d("SpeechToText", "Recognized: " + matches.get(0));
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        // Handle partial results if needed
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        // Handle events if needed
    }
}
