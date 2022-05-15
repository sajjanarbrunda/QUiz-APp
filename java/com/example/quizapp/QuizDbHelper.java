package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  com.example.quizapp.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper  extends SQLiteOpenHelper {
    public static final  String DATABASE_NAME = "GoQuiz";
    public static final  int DATABASE_VERSION= 1 ;

    private  SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       this.db = db;

       final String SQL_CREATE_QUESTIONS_TABLE= "CREATE TABLE " +
               QuestionTable.TABLE_NAME + " ( " +
               QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
               QuestionTable.COLUMN_QUESTION + " TEXT, " +
               QuestionTable.COLUMN_OPTION1 + " TEXT, " +
               QuestionTable.COLUMN_OPTION2 + " TEXT, " +
               QuestionTable.COLUMN_OPTION3 + " TEXT, " +
               QuestionTable.COLUMN_OPTION4 + " TEXT, " +
               QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
               ")" ;

          db.execSQL(SQL_CREATE_QUESTIONS_TABLE);


          fillQuestionTable();   //inserting data inside the table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " +QuestionTable.TABLE_NAME);
      onCreate(db);
    }

    private  void  addQuestion(Questions questions){

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, questions.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, questions.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, questions.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, questions.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, questions.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, questions.getAnswernr());

        db.insert(QuestionTable.TABLE_NAME,null, cv);

    }
   private  void fillQuestionTable(){
        Questions q1 = new Questions("Android is  what ?" , "os" ,"Drivers" ,"software" ,"hardware" ,1 );
        addQuestion(q1);

       Questions q2 = new Questions("What is the application class in android?" , "A class that can create only an object" ,"Anonymous class" ,"Java class" ,"Base class for all classes" ,4);
       addQuestion(q2);

       Questions q3 = new Questions("What is the life cycle of broadcast receivers in android?" , "send intent()" ," onRecieve()" ," implicitBroadcast()" ,"sendBroadcast()" ,2 );
       addQuestion(q3);

   }


   public ArrayList<Questions> getAllQuestions(){

        ArrayList<Questions> questionsList = new ArrayList<>();
        db = getReadableDatabase();
        String Projections[] ={
                QuestionTable._ID   ,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR




        };
       Cursor c = db.query(QuestionTable.TABLE_NAME,
               Projections,
               null,
               null,
               null,
               null,
               null


       );

       if(c.moveToFirst()){
           do{
               Questions questions = new Questions();
               questions.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
               questions.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
               questions.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
               questions.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
               questions.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
               questions.setAnswernr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

               questionsList.add(questions);

           }while(c.moveToNext());
       }
       c.close();
      return  questionsList;
   }




}
