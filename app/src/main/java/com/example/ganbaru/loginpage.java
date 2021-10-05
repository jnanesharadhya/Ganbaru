package com.example.ganbaru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginpage extends AppCompatActivity {

    TextView newUserRegister,resetPassword;
    EditText userName, password;
    Button login;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);
        newUserRegister = (TextView) findViewById(R.id.registerNew);
        resetPassword = (TextView) findViewById(R.id.resetPass);

        loading=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = userName.getText().toString();
                String pass1 = password.getText().toString();

                if(user1.isEmpty()) {
                    Toast.makeText(loginpage.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    userName.setError("Empty Username Field");
                    return;
                }
                else if(pass1.isEmpty()) {
                    Toast.makeText(loginpage.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    password.setError("Empty Password Field");
                    return;
                }
                else {
                    loading.setMessage("Logging In...");
                    loading.setTitle("Login");
                    loading.setCanceledOnTouchOutside(false);
                    loading.show();

                    mAuth.signInWithEmailAndPassword(user1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loading.dismiss();
                                Toast.makeText(loginpage.this,"Logged In",Toast.LENGTH_LONG).show();
                                Intent loadHome = new Intent (loginpage.this, homepage.class);
                                startActivity(loadHome);
                                finish();
                            }else {
                                loading.dismiss();
                                Toast.makeText(loginpage.this,"Wrong Credentials. Try again",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder resetPassDialog = new AlertDialog.Builder(v.getContext());
                resetPassDialog.setTitle("Reset Password");
                resetPassDialog.setMessage("Enter your linked Email ID:");
                resetPassDialog.setView(resetMail);

                resetPassDialog.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail= resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(loginpage.this,"Check Mail",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(loginpage.this,"Email not Registered",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                resetPassDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                resetPassDialog.show();
            }
        });

        newUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(loginpage.this,"Registration",Toast.LENGTH_LONG).show();
                Intent loadRegister = new Intent (loginpage.this, registrationpage.class);
                startActivity(loadRegister);
            }
        });
    }

}