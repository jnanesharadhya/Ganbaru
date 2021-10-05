package com.example.ganbaru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.Locale;

public class homepage extends AppCompatActivity {

    Button logout,Translatebtn;
    TextView Username;
    ProgressBar LoadDialog;
    TextToSpeech tts;
    EditText inpText, outText;
    Spinner fromLanguage;
    String[] LanguagesChoice= {"English","Arabic","Bengali","Hindi","Kannada","Urdu"};

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDB;
    String UserID;

    private static final int REQUEST_PERMISSION_CODE = 1;
    String JPCode, chosenLanguageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        logout = (Button) findViewById(R.id.logoutButton);
        Username = (TextView) findViewById(R.id.userDisplay);
        LoadDialog =(ProgressBar)findViewById(R.id.progressBar);
        inpText =(EditText) findViewById(R.id.inputSearch);
        outText =(EditText) findViewById(R.id.outputResult);
        Translatebtn =(Button) findViewById(R.id.btnTranslate);
        fromLanguage =(Spinner) findViewById(R.id.LanguageSpinner);

        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();

        LoadDialog.setVisibility(View.VISIBLE);

        if(inpText.getText().toString().isEmpty()){
            outText.setText("");
        }

        tts = new TextToSpeech(getBaseContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int Status) {
                if (Status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.JAPAN);
                }
            }
        });

        fromLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosenLanguageCode = getLangCode(LanguagesChoice[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter fromAdapter = new ArrayAdapter(this,R.layout.spinner_item,LanguagesChoice);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromLanguage.setAdapter(fromAdapter);

        JPCode = TranslateLanguage.JAPANESE;

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homepage.this,"Logged Out",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(new Intent(homepage.this,loginpage.class));
                finish();
            }
        });

        mDB = FirebaseDatabase.getInstance().getReference("RegUsers");
        UserID =mUser.getUid();

        mDB.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userProfile = snapshot.getValue(user.class);
                if(userProfile != null){
                    String name = userProfile.username;
                    LoadDialog.setVisibility(View.GONE);
                    Username.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(homepage.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        Translatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outText.setText("");
                if(inpText.getText().toString().isEmpty()){
                    Toast.makeText(homepage.this,"Please Add the Input Text",Toast.LENGTH_SHORT).show();
                }else if(chosenLanguageCode.isEmpty()){
                    Toast.makeText(homepage.this,"Choose A Language",Toast.LENGTH_SHORT).show();
                }else{
                    TranslateText(chosenLanguageCode,JPCode,inpText.getText().toString());
                }
            }
        });

    }

    private void TranslateText(String fromL, String JPL, String TextMsg){
        outText.setText("Downloading Model...");
        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(fromL)
                .setTargetLanguage(JPL)
                .build();

        Translator translator = Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder().build();

        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                outText.setText("Translating...");
                translator.translate(TextMsg).addOnSuccessListener(new OnSuccessListener<String>() {
                      @Override
                      public void onSuccess(String s) {

                          outText.setText(s);
                          tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                      }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(homepage.this,"Translation Failure"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(homepage.this,"Model Download Failure"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public  String getLangCode(String Lang){
        String langCode;
        switch (Lang){
            case"English":
                langCode = TranslateLanguage.ENGLISH;
                break;

            case"Arabic":
                langCode = TranslateLanguage.ARABIC;
                break;

            case"Bengali":
                langCode = TranslateLanguage.BENGALI;
                break;

            case"Hindi":
                langCode = TranslateLanguage.HINDI;
                break;

            case"Kannada":
                langCode = TranslateLanguage.KANNADA;
                break;

            case"Urdu":
                langCode = TranslateLanguage.URDU;
                break;

            default:
                langCode = "";
        }

        return langCode;
    }

    public void alphabetPage(View view) {
        Intent loadAlphabetPage = new Intent (homepage.this, alphabet.class);
        startActivity(loadAlphabetPage);
    }

    public void numberPage(View view) {
        Intent loadNumberPage = new Intent (homepage.this, number.class);
        startActivity(loadNumberPage);
    }

    public void animalsPage(View view) {
        Intent loadAnimalPage = new Intent (homepage.this, animal.class);
        startActivity(loadAnimalPage);
    }

    public void wordPage(View view) {
        Intent loadWordPage = new Intent (homepage.this, word.class);
        startActivity(loadWordPage);
    }

    public void phrasePage(View view) {
        Intent loadPhrasePage = new Intent (homepage.this, phrase.class);
        startActivity(loadPhrasePage);
    }
}