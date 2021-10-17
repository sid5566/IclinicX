package com.example.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        EditText Email,Password;
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Button Logins;
        ProgressBar progressBar;
        progressBar = findViewById(R.id.progressBar2);
        Logins = findViewById(R.id.Login);
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        Logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String UEmail = Email.getText().toString();
                String UPassword = Password.getText().toString();
                if (UEmail.isEmpty() || UPassword.isEmpty()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Login.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                } else {


                    auth.signInWithEmailAndPassword(UEmail, UPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Intent i = new Intent(Login.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Login.this, "Please Create Account", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }

        });
        TextView Createaccount;
        Createaccount = findViewById(R.id.Createaccount);
        Createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Signup.class);
                startActivity(i);
            }
        });
        TextView ForgetPassT;
        ForgetPassT = findViewById(R.id.ForgetPassT);
        ForgetPassT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Forget.class);
                startActivity(i);
            }
        });
        if(auth.getCurrentUser()!=null)
        {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}