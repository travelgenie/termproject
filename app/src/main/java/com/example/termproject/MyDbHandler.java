package com.example.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbHandler extends SQLiteOpenHelper {
    private static String key_question = "question";
    private static String key_option1 = "option1";
    private static String key_option2 = "option2";
    private static String key_option3 = "option3";
    private static String key_option4 = "option4";
    private static String key_correct_option = "correct_option";
    private static String key_table = "question_table";
    private static String key_database = "quiz_db";
    private static final int database_version = 1;

    public MyDbHandler(@Nullable Context context) {
        super(context, key_database, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + key_table + " (" +
                key_question + " TEXT, " +
                key_option1 + " TEXT, " +
                key_option2 + " TEXT, " +
                key_option3 + " TEXT, " +
                key_option4 + " TEXT, " +
                key_correct_option + " TEXT);");
    }


    public int insertData(QuizDetails quizDetails) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key_question, quizDetails.getQuestion_detail());
        contentValues.put(key_option1, quizDetails.getOption1());
        contentValues.put(key_option2, quizDetails.getOption2());
        contentValues.put(key_option3, quizDetails.getOption3());
        contentValues.put(key_option4, quizDetails.getOption4());
        contentValues.put(key_correct_option, quizDetails.getCorrect_option());
        sqLiteDatabase.insert(key_table, null, contentValues);
        return 1;
    }

    public ArrayList<QuizDetails> getAllData(){
        ArrayList<QuizDetails> details = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + key_table;
        Cursor row = sqLiteDatabase.rawQuery(query, null);
        // moving our cursor to first position.
        if (row.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                details.add(new QuizDetails(row.getString(0), row.getString(1),
                        row.getString(2),
                        row.getString(3),row.getString(4),row.getString(5)));
            } while (row.moveToNext());
            // moving our cursor to next.
        }
        row.close();
        return details;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + key_table);
        onCreate(sqLiteDatabase);
    }
}
