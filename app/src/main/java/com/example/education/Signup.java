package com.example.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        EditText Name, Email, Password;
        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        ProgressBar progressBar;
        progressBar = findViewById(R.id.progressBar);
        Button Signupa;
        Signupa = findViewById(R.id.Signup);
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();
        Signupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String UName = Name.getText().toString();
                String UEmail = Email.getText().toString();
                String UPassword = Password.getText().toString();
                if(UName.isEmpty() || UEmail.isEmpty() || UPassword.isEmpty() || UPassword.length()<6)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Signup.this, "Fields are Empty or Your Password is Less than 6 Characters", Toast.LENGTH_SHORT).show();
                }
                else
                {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Signup.this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("string_id", UName); //InputString: from the EditText
                editor.commit();
                    auth.createUserWithEmailAndPassword(UEmail,UPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                String id = auth.getUid();
                                inputModel inputModel = new inputModel(UName,UEmail,UPassword,id);
                                database.getReference().child("User").child(auth.getUid()).setValue(inputModel);
                                Intent i  = new Intent(Signup.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Signup.this, "Already Account Created", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }}
        });

    }
    }
