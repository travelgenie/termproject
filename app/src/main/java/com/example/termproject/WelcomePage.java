package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomePage extends AppCompatActivity {

    Button createquiz, startQuiz;
    TextView welcome;
    String user = "Welcome ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        createquiz = (Button) findViewById(R.id.createquiz);
        startQuiz = (Button) findViewById(R.id.startquiz);
        if (getIntent().hasExtra("User")) {
            user += getIntent().getStringExtra("User");
        }
        welcome = (TextView) findViewById(R.id.login);
        welcome.setText(user);
        MyDbHandler myDbHandler = new MyDbHandler(getApplicationContext());
        if (myDbHandler.getAllData().size() <= 0) {
            myDbHandler.insertData(new QuizDetails("Who was Jack the Reaper?","Unknown","Prudhvi","Amandeep","Bhvesh","A"));
            myDbHandler.insertData(new QuizDetails("What is the capital of canada?","Toronto","Ottawa","london","waterloo","B"));
            myDbHandler.insertData(new QuizDetails("what is 2+2?","1","2","4","11","C"));
            myDbHandler.insertData(new QuizDetails("which is a better coding language for Android?","Java","Csharp","cpp","kotlin","D"));
            myDbHandler.insertData(new QuizDetails("what is the capital of poland?","Lodz","warsaw","lublin","i quit","B"));
            myDbHandler.insertData(new QuizDetails("What is the capital of Spain?","Barcelona","Ibiza","Madrid","Cádiz","C"));
            myDbHandler.insertData(new QuizDetails("What is the capital of England?","London","Liverpool","Manchester","Brighton","A"));
            myDbHandler.insertData(new QuizDetails("Which country has strongest defense budget?","India","Russia","China","USA","D"));
            myDbHandler.insertData(new QuizDetails("Which country has strongest defense?","Canada","Russia","USA","Istanbul","C"));
            myDbHandler.insertData(new QuizDetails("Who killed JF kenedy ?","CIA","FBI","Illuminati","Interpol","C"));
        }

        startQuiz.setOnClickListener(view ->
        {
            Intent intent = new Intent(getApplicationContext(), QuizPage.class);
            startActivity(intent);
            finish();

        });

        createquiz.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CreateQuiz.class);
            startActivity(intent);
            finish();
        });


    }
}