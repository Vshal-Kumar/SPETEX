package com.example.myapp;

import java.util.List;

public class WordDefinition {
    private final String word;
    private List<Meaning> meanings;

    public WordDefinition(String word, List<Meaning> meanings) {
        this.word = word;
        this.meanings = meanings;
    }

    public String getWord() {
        return word;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public static class Meaning {
        private String partOfSpeech;
        private List<Definition> definitions;

        public Meaning(String partOfSpeech, List<Definition> definitions) {
            this.partOfSpeech = partOfSpeech;
            this.definitions = definitions;
        }

        public String getPartOfSpeech() {
            return partOfSpeech;
        }

        public List<Definition> getDefinitions() {
            return definitions;
        }

        public static class Definition {
            private String definition;

            public Definition(String definition) {
                this.definition = definition;
            }

            public String getDefinition() {
                return definition;
            }
        }
    }
}