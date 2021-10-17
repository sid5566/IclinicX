package com.example.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class consult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        getSupportActionBar().hide();
        TextView heading;
        ImageView imageConsult;
        imageConsult = findViewById(R.id.imageConsult);
        heading = findViewById(R.id.heading);
        Intent intent = getIntent();
        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();
        EditText Problem,Number;
        Problem = findViewById(R.id.Problem);
        Number = findViewById(R.id.Number);
        FirebaseAuth  auth;
        auth = FirebaseAuth.getInstance();
        String str = intent.getStringExtra("heading");
        heading.setText(str);
        if(str.equals(new String("Ayurveda Consultation")))
        {
            imageConsult.setImageResource(R.drawable.ayurvedicli);
        }
        else if(str.equals(new String("Yoga")))
        {
            imageConsult.setImageResource(R.drawable.yogaconsult);
        }
        else if(str.equals(new String("COVID Consultation")))
        {
            imageConsult.setImageResource(R.drawable.covidconsult);
        }
        else if(str.equals(new String("Naturopathy")))
        {
            imageConsult.setImageResource(R.drawable.ayurvedicconsult);
        }
        else if(str.equals(new String("Homeopathy")))
        {
            imageConsult.setImageResource(R.drawable.homeopathyconsult);
        }
        else
        {
            imageConsult.setImageResource(R.drawable.consult);
        }

        Button consultClick;
        ImageView Sub;
        Sub = findViewById(R.id.Subbmited);
        consultClick = findViewById(R.id.consultClick);
        consultClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sub.setVisibility(View.VISIBLE);
                String Uproblem = Problem.getText().toString();
                String UNumber = Number.getText().toString();
                if (Uproblem.isEmpty() || UNumber.isEmpty()) {
                    Toast.makeText(consult.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                } else {


                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(consult.this);
                    String data = prefs.getString("string_id", "no id");
                    ProblemModel problemModel = new ProblemModel(Uproblem, UNumber, data);
                    database.getReference().child("User Data").child(auth.getUid()).child(str).push().setValue(problemModel);
                    Thread th = new Thread() {
                        public void run() {
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                Intent I = new Intent(consult.this, MainActivity.class);
                                startActivity(I);
                                finish();
                            }
                        }
                    };
                    th.start();
                }
            }
        });

    }
}