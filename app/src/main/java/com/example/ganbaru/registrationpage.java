package com.example.ganbaru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class registrationpage extends AppCompatActivity {

    TextView loginAgain;
    EditText rusername, rpassword,remail,rphone;
    Button register;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog loading;
    String mailRE = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);

        rusername = (EditText) findViewById(R.id.rusername);
        rpassword = (EditText) findViewById(R.id.rpassword);
        remail = (EditText) findViewById(R.id.remail);
        rphone = (EditText) findViewById(R.id.rphone);
        register = (Button) findViewById(R.id.registerButton);
        loginAgain = (TextView) findViewById(R.id.toLogin);

        loading=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();

        loginAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadlogin = new Intent (registrationpage.this, loginpage.class);
                startActivity(loadlogin);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernametxt = rusername.getText().toString();
                String passwordtxt = rpassword.getText().toString();
                String emailtxt = remail.getText().toString();
                String phonetxt = rphone.getText().toString();

                if(phonetxt.isEmpty()){
                    Toast.makeText(registrationpage.this,"Enter Phone", Toast.LENGTH_SHORT).show();
                    rphone.setError("Empty Phone Field");
                    return;
                }
                else if(emailtxt.isEmpty()){
                    Toast.makeText(registrationpage.this,"Enter Email", Toast.LENGTH_SHORT).show();
                    remail.setError("Empty Email Field");
                    return;
                }
                else if(passwordtxt.isEmpty()){
                    Toast.makeText(registrationpage.this,"Enter Password", Toast.LENGTH_SHORT).show();
                    rpassword.setError("Empty Password Field");
                    return;
                }
                else if(usernametxt.isEmpty()){
                    Toast.makeText(registrationpage.this,"Enter Username", Toast.LENGTH_SHORT).show();
                    rusername.setError("Empty Username Field");
                    return;
                }
                else if (!emailtxt.matches(mailRE)){
                    remail.setError("Enter Valid Mail");
                    remail.requestFocus();
                    return;
                }
                else if(rpassword.length()<6){
                    rpassword.setError("Too Short");
                    rpassword.requestFocus();
                    return;
                }
                else{

                    loading.setMessage("Registering new user...");
                    loading.setTitle("Registration");
                    loading.setCanceledOnTouchOutside(false);
                    loading.show();

                    mAuth.createUserWithEmailAndPassword(emailtxt,passwordtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                user registerdata = new user(usernametxt,emailtxt,phonetxt);
                                FirebaseDatabase.getInstance().getReference("RegUsers")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(registerdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull  Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(registrationpage.this,"User Registered",Toast.LENGTH_SHORT).show();
                                            loading.dismiss();
                                            Intent loadHome = new Intent (registrationpage.this, loginpage.class);
                                            startActivity(loadHome);
                                            finish();

                                        }else{
                                            loading.dismiss();
                                            Toast.makeText(registrationpage.this,"Cannot Add User",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }else {

                                loading.dismiss();
                                Toast.makeText(registrationpage.this,"Wrong Credentials. Try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}