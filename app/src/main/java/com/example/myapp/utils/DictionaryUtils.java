package com.example.myapp.utils;

import java.util.function.Consumer;

public class DictionaryUtils {

    public static void lookupWord(String word, Consumer<String> callback) {
        // Placeholder for actual dictionary API call
        callback.accept("Sample definition of " + word);
    }
}
