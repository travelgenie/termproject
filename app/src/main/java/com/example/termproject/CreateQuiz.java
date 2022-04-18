package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateQuiz extends AppCompatActivity {

    EditText Question_Detail, Option1, Option2, Option3, Option4, CorrectOption;
    Button AddQuiz, Done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        Question_Detail = findViewById(R.id.addquestion);
        Option1 = findViewById(R.id.addoption1);
        Option2 = findViewById(R.id.addoption2);
        Option3 = findViewById(R.id.addoption3);
        Option4 = findViewById(R.id.addoption4);
        AddQuiz = findViewById(R.id.addtoquiz);
        CorrectOption = findViewById(R.id.correctoption);
        Done = findViewById(R.id.done);
        AddQuiz.setOnClickListener(view -> {
            if (Question_Detail.getText().toString().equals("")) {
                Question_Detail.setError("Add Question Detail");
            } else if (Option1.getText().toString().equals("")) {
                Option1.setError("Add Option 1");
            } else if (Option2.getText().toString().equals("")) {
                Option2.setError("Add Option 2");
            } else if (Option3.getText().toString().equals("")) {
                Option3.setError("Add Option 3");
            } else if (Option4.getText().toString().equals("")) {
                Option4.setError("Add Option 4");
            } else if (CorrectOption.getText().toString().equals("")) {
                CorrectOption.setError("Add Correct Option");
            } else {
                QuizDetails quizDetails=new QuizDetails();
                quizDetails.setQuestion_detail(Question_Detail.getText().toString());
                quizDetails.setOption1(Option1.getText().toString());
                quizDetails.setOption2(Option2.getText().toString());
                quizDetails.setOption3(Option3.getText().toString());
                quizDetails.setOption4(Option4.getText().toString());
                quizDetails.setCorrect_option(CorrectOption.getText().toString());

                MyDbHandler myDbHandler=new MyDbHandler(getApplicationContext());
              int i=myDbHandler.insertData(quizDetails);
              if(i==1)
              {
                  Toast.makeText(getApplicationContext(), "Question Added Succesfully", Toast.LENGTH_LONG).show();
              }
              else
              {
                  Toast.makeText(getApplicationContext(), "Error While Adding Question", Toast.LENGTH_LONG).show();
              }
            }


        });


        Done.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
            startActivity(intent);
            finish();
        });


    }
}