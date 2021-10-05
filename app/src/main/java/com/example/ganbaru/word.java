package com.example.ganbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class word extends AppCompatActivity implements View.OnClickListener {
    TextToSpeech tts;
    ImageButton wrd1, wrd2, wrd3, wrd4, wrd5, wrd6, wrd7, wrd8, wrd9, wrd10, wrd11, wrd12, wrd13;
    TextView wordtxt1, wordtxt2, wordtxt3, wordtxt4, wordtxt5, wordtxt6, wordtxt7, wordtxt8,wordtxt9, wordtxt10, wordtxt11, wordtxt12, wordtxt13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        wrd1 = findViewById(R.id.btnW1);
        wrd2 = findViewById(R.id.btnW2);
        wrd3 = findViewById(R.id.btnW3);
        wrd4 = findViewById(R.id.btnW4);
        wrd5 = findViewById(R.id.btnW5);
        wrd6 = findViewById(R.id.btnW6);
        wrd7 = findViewById(R.id.btnW7);
        wrd8 = findViewById(R.id.btnW8);
        wrd9 = findViewById(R.id.btnW9);
        wrd10 = findViewById(R.id.btnW10);
        wrd11 = findViewById(R.id.btnW11);
        wrd12 = findViewById(R.id.btnW12);
        wrd13 = findViewById(R.id.btnW13);

        wordtxt1 = findViewById(R.id.word1);
        wordtxt2 = findViewById(R.id.word2);
        wordtxt3 = findViewById(R.id.word3);
        wordtxt4 = findViewById(R.id.word4);
        wordtxt5 = findViewById(R.id.word5);
        wordtxt6 = findViewById(R.id.word6);
        wordtxt7 = findViewById(R.id.word7);
        wordtxt8 = findViewById(R.id.word8);
        wordtxt9 = findViewById(R.id.word9);
        wordtxt10 = findViewById(R.id.word10);
        wordtxt11 = findViewById(R.id.word11);
        wordtxt12 = findViewById(R.id.word12);
        wordtxt13 = findViewById(R.id.word13);

        wrd1.setOnClickListener(this);
        wrd2.setOnClickListener(this);
        wrd3.setOnClickListener(this);
        wrd4.setOnClickListener(this);
        wrd5.setOnClickListener(this);
        wrd6.setOnClickListener(this);
        wrd7.setOnClickListener(this);
        wrd8.setOnClickListener(this);
        wrd9.setOnClickListener(this);
        wrd10.setOnClickListener(this);
        wrd11.setOnClickListener(this);
        wrd12.setOnClickListener(this);
        wrd13.setOnClickListener(this);

        tts = new TextToSpeech(getBaseContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int Status) {
                if (Status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.JAPAN);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnW1:
                String W1 = wordtxt1.getText().toString();
                speakText(W1);
                break;

            case R.id.btnW2:
                String W2 = wordtxt2.getText().toString();
                speakText(W2);
                break;

            case R.id.btnW3:
                String W3 = wordtxt3.getText().toString();
                speakText(W3);
                break;

            case R.id.btnW4:
                String W4 = wordtxt4.getText().toString();
                speakText(W4);
                break;

            case R.id.btnW5:
                String W5 = wordtxt5.getText().toString();
                speakText(W5);
                break;

            case R.id.btnW6:
                String W6 = wordtxt6.getText().toString();
                speakText(W6);
                break;

            case R.id.btnW7:
                String W7 = wordtxt7.getText().toString();
                speakText(W7);
                break;

            case R.id.btnW8:
                String W8 = wordtxt8.getText().toString();
                speakText(W8);
                break;

            case R.id.btnW9:
                String W9 = wordtxt9.getText().toString();
                speakText(W9);
                break;

            case R.id.btnW10:
                String W10 = wordtxt10.getText().toString();
                speakText(W10);
                break;

            case R.id.btnW11:
                String W11 = wordtxt11.getText().toString();
                speakText(W11);
                break;

            case R.id.btnW12:
                String W12 = wordtxt12.getText().toString();
                speakText(W12);
                break;

            case R.id.btnW13:
                String W13 = wordtxt13.getText().toString();
                speakText(W13);
                break;


        }
    }

    public void speakText(String text) {

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}