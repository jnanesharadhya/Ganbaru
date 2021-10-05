package com.example.ganbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class number extends AppCompatActivity implements View.OnClickListener {
    TextToSpeech tts;
    ImageButton num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,num10;
    TextView phrase0,phrase1, phrase2, phrase3, phrase4,phrase5, phrase6, phrase7, phrase8,phrase9, phrase10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        num0 = findViewById(R.id.num0);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        num10 = findViewById(R.id.num10);

        phrase0 = findViewById(R.id.read0);
        phrase1 = findViewById(R.id.read1);
        phrase2 = findViewById(R.id.read2);
        phrase3 = findViewById(R.id.read3);
        phrase4 = findViewById(R.id.read4);
        phrase5 = findViewById(R.id.read5);
        phrase6 = findViewById(R.id.read6);
        phrase7 = findViewById(R.id.read7);
        phrase8 = findViewById(R.id.read8);
        phrase9 = findViewById(R.id.read9);
        phrase10 = findViewById(R.id.read10);

        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num10.setOnClickListener(this);

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
            case R.id.num0:
                String Ph0 = phrase0.getText().toString();
                speakText(Ph0);
                break;

            case R.id.num1:
                String Ph1 = phrase1.getText().toString();
                speakText(Ph1);
                break;

            case R.id.num2:
                String Ph2 = phrase2.getText().toString();
                speakText(Ph2);
                break;

            case R.id.num3:
                String Ph3 = phrase3.getText().toString();
                speakText(Ph3);
                break;

            case R.id.num4:
                String Ph4 = phrase4.getText().toString();
                speakText(Ph4);
                break;

            case R.id.num5:
                String Ph5 = phrase5.getText().toString();
                speakText(Ph5);
                break;

            case R.id.num6:
                String Ph6 = phrase6.getText().toString();
                speakText(Ph6);
                break;

            case R.id.num7:
                String Ph7 = phrase7.getText().toString();
                speakText(Ph7);
                break;

            case R.id.num8:
                String Ph8 = phrase8.getText().toString();
                speakText(Ph8);
                break;

            case R.id.num9:
                String Ph9 = phrase9.getText().toString();
                speakText(Ph9);
                break;

            case R.id.num10:
                String Ph10 = phrase10.getText().toString();
                speakText(Ph10);
                break;

        }
    }

    public void speakText(String text) {

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}