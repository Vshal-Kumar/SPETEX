package com.example.myapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DictionaryActivity extends AppCompatActivity {

    private EditText wordInput;
    private TextView definitionOutput;
    private Button searchButton;

    private static final String BASE_URL = "https://api.dictionaryapi.dev/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        // Initialize the views
        wordInput = findViewById(R.id.word_input);
        definitionOutput = findViewById(R.id.definition_output);
        searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(v -> {
            String word = wordInput.getText().toString();
            if (!word.isEmpty()) {
                fetchDefinition(word);
            } else {
                Toast.makeText(DictionaryActivity.this, "Please enter a word", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchDefinition(String word) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DictionaryApi dictionaryApi = retrofit.create(DictionaryApi.class);
        Call<List<WordDefinition>> call = dictionaryApi.getWordDefinition(word);

        call.enqueue(new Callback<List<WordDefinition>>() {
            @Override
            public void onResponse(@NonNull Call<List<WordDefinition>> call, @NonNull Response<List<WordDefinition>> response) {
                // Log the full response to inspect what we are receiving from the API
                Log.d("DictionaryActivity", "Response code: " + response.code());
                Log.d("DictionaryActivity", "Response body: " + response.body());

                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    List<WordDefinition> definitions = response.body();
                    StringBuilder output = new StringBuilder();
                    for (WordDefinition definition : definitions) {
                        output.append("<b>Word:</b> ").append(definition.getWord()).append("<br/>");
                        for (WordDefinition.Meaning meaning : definition.getMeanings()) {
                            output.append("<b>Meaning:</b> ").append(meaning.getPartOfSpeech()).append("<br/>");
                            for (WordDefinition.Meaning.Definition def : meaning.getDefinitions()) {
                                output.append("• <b>Definition:</b> ").append(def.getDefinition()).append("<br/>");
                            }
                        }
                    }
                    definitionOutput.setText(android.text.Html.fromHtml(output.toString(), android.text.Html.FROM_HTML_MODE_LEGACY));
                } else {
                    // Log the error response
                    Log.e("DictionaryActivity", "Error: No definition found for the word " + word);
                    Toast.makeText(DictionaryActivity.this, "No definition found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<WordDefinition>> call, @NonNull Throwable t) {
                // Log the error message
                Log.e("DictionaryActivity", "Error fetching definition: " + t.getMessage());
                Toast.makeText(DictionaryActivity.this, "Error fetching definition", Toast.LENGTH_SHORT).show();
            }
        });
    }
}