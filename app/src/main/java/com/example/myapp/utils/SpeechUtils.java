package com.example.myapp.utils;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import java.util.Locale;

public class SpeechUtils {

    public static Intent getSpeechRecognitionIntent() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        return intent;
    }
}
