package com.example.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        getSupportActionBar().hide();
        Button SendForget;
        EditText L_Email;
        L_Email = findViewById(R.id.L_Email);
        SendForget = findViewById(R.id.SendForget);
        SendForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = L_Email.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Forget.this, "Password reset Link has been Send To Your Email Account", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Forget.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}