package com.example.myapplication;

public class Dataholder {
    private static Dataholder instance;
    private String finalText;

    private void Dataholder() {
        // Initialize finalText to an empty string
        finalText = "";
    }

    public static synchronized Dataholder getInstance() {
        if (instance == null) {
            instance = new Dataholder();
        }
        return instance;
    }

    public String getFinalText() {
        return finalText;
    }

    public void setFinalText(String text) {
        this.finalText = text;
    }
}
