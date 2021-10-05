package com.example.ganbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class animal extends AppCompatActivity implements View.OnClickListener {

    TextToSpeech tts;
    TextView ani1,ani2,ani3,ani4,ani5,ani6,ani7,ani8,ani9,ani10;
    ImageButton animal1,animal2,animal3,animal4,animal5,animal6,animal7,animal8,animal9,animal10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        animal1 = findViewById(R.id.animal1);
        animal2 = findViewById(R.id.animal2);
        animal3 = findViewById(R.id.animal3);
        animal4 = findViewById(R.id.animal4);
        animal5 = findViewById(R.id.animal5);
        animal6 = findViewById(R.id.animal6);
        animal7 = findViewById(R.id.animal7);
        animal8 = findViewById(R.id.animal8);
        animal9 = findViewById(R.id.animal9);

        ani1 = findViewById(R.id.ani1);
        ani2 = findViewById(R.id.ani2);
        ani3 = findViewById(R.id.ani3);
        ani4 = findViewById(R.id.ani4);
        ani5 = findViewById(R.id.ani5);
        ani6 = findViewById(R.id.ani6);
        ani7 = findViewById(R.id.ani7);
        ani8 = findViewById(R.id.ani8);
        ani9 = findViewById(R.id.ani9);

        animal1.setOnClickListener(this);
        animal2.setOnClickListener(this);
        animal3.setOnClickListener(this);
        animal4.setOnClickListener(this);
        animal5.setOnClickListener(this);
        animal6.setOnClickListener(this);
        animal7.setOnClickListener(this);
        animal8.setOnClickListener(this);
        animal9.setOnClickListener(this);

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
            case R.id.animal1:
                String Ph0 = ani1.getText().toString();
                speakText(Ph0);
                break;

            case R.id.animal2:
                String Ph1 = ani2.getText().toString();
                speakText(Ph1);
                break;

            case R.id.animal3:
                String Ph2 = ani3.getText().toString();
                speakText(Ph2);
                break;

            case R.id.animal4:
                String Ph3 = ani4.getText().toString();
                speakText(Ph3);
                break;

            case R.id.animal5:
                String Ph4 = ani5.getText().toString();
                speakText(Ph4);
                break;

            case R.id.animal6:
                String Ph5 = ani6.getText().toString();
                speakText(Ph5);
                break;

            case R.id.animal7:
                String Ph6 = ani7.getText().toString();
                speakText(Ph6);
                break;

            case R.id.animal8:
                String Ph7 = ani8.getText().toString();
                speakText(Ph7);
                break;

            case R.id.animal9:
                String Ph8 = ani9.getText().toString();
                speakText(Ph8);
                break;

        }
    }

    public void speakText(String text) {

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}