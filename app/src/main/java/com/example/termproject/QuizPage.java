package com.example.termproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class QuizPage extends AppCompatActivity {
    private Button submit;
    private TextView numberOfQuestions, question;
    private RadioGroup radioGroup;
    private ArrayList<QuizDetails> details = new ArrayList<>();
    private String selectedOption = "";
    private int position = 0;
    ArrayList<String> optionsSelected = new ArrayList<>();
    private RadioButton optionA,optionB,optionC,optionD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        numberOfQuestions = (TextView)findViewById(R.id.idTVQuestionAttempted);
        question = (TextView)findViewById(R.id.idTVQuestion);
        radioGroup = (RadioGroup)findViewById(R.id.radio_group_option);
        optionA = (RadioButton)findViewById(R.id.A);
        optionB= (RadioButton)findViewById(R.id.B);
        optionC = (RadioButton)findViewById(R.id.C);
        optionD = (RadioButton)findViewById(R.id.D);
        MyDbHandler myDbHandler = new MyDbHandler(getApplicationContext());
        details = myDbHandler.getAllData();
        Collections.shuffle(details);
        radioGroup.setOnCheckedChangeListener((radioGroup, _id) -> selectionChoice(radioGroup));
        submit = (Button) findViewById(R.id.submit_quiz);
        submit.setOnClickListener(view -> {
            if(radioGroup.getCheckedRadioButtonId()!=-1){//Radio Button Is Checked
                position+=1;
                if (position == details.size()) {
                    int score = 0;
                    optionsSelected.add(selectedOption);

                    for (int i=0;i<=details.size()-1;i++){
                        if (optionsSelected.get(i).equalsIgnoreCase(details.get(i).correct_option)){
                            score+=1;
                        }
                    }
                    showDialog(this, String.valueOf(score));

                }else{
                    radioGroup.clearCheck();
                    optionsSelected.add(selectedOption);
                    setData(position);
                }
            }else{
                Toast.makeText(this,"Please choose an option", Toast.LENGTH_LONG).show();
            }


        });
        setData(position);
    }

    private void setData(int position){
        if(position == details.size()-1){
            submit.setText("SUBMIT");
        }
        numberOfQuestions.setText("Question\n"+(position+1)+"/"+(details.size()));
        question.setText(details.get(position).getQuestion_detail());
        optionA.setText(details.get(position).getOption1());
        optionB.setText(details.get(position).getOption2());
        optionC.setText(details.get(position).getOption3());
        optionD.setText(details.get(position).getOption4());

    }

    private void selectionChoice(RadioGroup radioGroup){
        int checkedRadioId = radioGroup.getCheckedRadioButtonId();
        if(checkedRadioId== R.id.A) {
            selectedOption = "A";
        } else if(checkedRadioId== R.id.B ) {
            selectedOption = "B";
        } else if(checkedRadioId== R.id.C) {
            selectedOption = "C";
        }else if(checkedRadioId== R.id.D) {
            selectedOption = "D";
        }
    }

    private void showDialog(Context context,String score){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("The Impossible Quiz");
        builder.setMessage("you scored "+score+"/"+details.size());
        builder.setCancelable(false);
        builder.setPositiveButton(
                "OK",
                (dialog, id) -> {
                    Intent intent = new Intent(QuizPage.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                });

        AlertDialog alert = builder.create();
        alert.show();
    }


}