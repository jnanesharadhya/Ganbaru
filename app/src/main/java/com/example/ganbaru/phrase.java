package com.example.ganbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class phrase extends AppCompatActivity implements View.OnClickListener {
    TextToSpeech tts;
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    TextView phrase1, phrase2, phrase3, phrase4, phrase5, phrase6, phrase7, phrase8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);

        btn1 = findViewById(R.id.btnP1);
        btn2 = findViewById(R.id.btnP2);
        btn3 = findViewById(R.id.btnP3);
        btn4 = findViewById(R.id.btnP4);
        btn5 = findViewById(R.id.btnP5);
        btn6 = findViewById(R.id.btnP6);
        btn7 = findViewById(R.id.btnP7);
        btn8 = findViewById(R.id.btnP8);

        phrase1 = findViewById(R.id.phrase1);
        phrase2 = findViewById(R.id.phrase2);
        phrase3 = findViewById(R.id.phrase3);
        phrase4 = findViewById(R.id.phrase4);
        phrase5 = findViewById(R.id.phrase5);
        phrase6 = findViewById(R.id.phrase6);
        phrase7 = findViewById(R.id.phrase7);
        phrase8 = findViewById(R.id.phrase8);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

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
            case R.id.btnP1:
                String Ph1 = phrase1.getText().toString();
                speakText(Ph1);
                break;

            case R.id.btnP2:
                String Ph2 = phrase2.getText().toString();
                speakText(Ph2);
                break;

            case R.id.btnP3:
                String Ph3 = phrase3.getText().toString();
                speakText(Ph3);
                break;

            case R.id.btnP4:
                String Ph4 = phrase4.getText().toString();
                speakText(Ph4);
                break;

            case R.id.btnP5:
                String Ph5 = phrase5.getText().toString();
                speakText(Ph5);
                break;

            case R.id.btnP6:
                String Ph6 = phrase6.getText().toString();
                speakText(Ph6);
                break;

            case R.id.btnP7:
                String Ph7 = phrase7.getText().toString();
                speakText(Ph7);
                break;

            case R.id.btnP8:
                String Ph8 = phrase8.getText().toString();
                speakText(Ph8);
                break;
        }

    }

    public void speakText(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}