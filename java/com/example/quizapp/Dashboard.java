package com.example.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.PrimitiveIterator;

public class Dashboard  extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton rb1 , rb2 , rb3 , rb4;
    private Button next;
    private TextView tv_questions, tv_score , tv_countdown , tv_questioncount;

    private ArrayList<Questions> questionslist;
    private  int questioncounter ;
    private  int questiontotalcount ;
    private  Questions currentQuestions;
    private  boolean answered;

    private Handler handler = new Handler();
    private ColorStateList defaultTextColor;

    private int correctans=0 , wrongans=0;
    private FinalScoreDialog finalScoreDialog;

    private int totalSizeofQuiz = 0;
    private  static  final  long COUNTDOWN_IN_MILLS = 10000;
    private CountDownTimer countDownTimer;
    private  long timeleftinMills;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        setupUI();   // intilizatining the components
        fetchDB();   // fecting the database
        defaultTextColor = rb1.getTextColors();  // getting the color of button
        finalScoreDialog = new FinalScoreDialog(this);
    }
    private  void setupUI(){
        radioGroup = findViewById(R.id.Radio_group);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        next = findViewById(R.id.btn_Next);
        tv_questioncount = findViewById(R.id.tv_questionumber);
        tv_questions = findViewById(R.id.tv_question);
        tv_score = findViewById(R.id.tv_score);
       tv_countdown = findViewById(R.id.tv_time);

    }
    private  void fetchDB(){
        QuizDbHelper dbhelper = new QuizDbHelper(this);    // object cretion of database class
        questionslist =  dbhelper.getAllQuestions();
        
        
        startQuiz();
    }

    private void startQuiz() {

        questiontotalcount = questionslist.size();
        Collections.shuffle(questionslist);    //shuffle the question using random access
        showQuestions();  //call showQUestion methos

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                         break;
                    case R.id.radioButton2:
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_selected));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        break;
                    case R.id.radioButton3:
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        break;
                    case R.id.radioButton4:
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
                        break;

                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        quizOpertions();
                    }else{
                        Toast.makeText(Dashboard.this ,"please select options" ,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }

    private void quizOpertions() {
        answered = true;
        countDownTimer.cancel();
        RadioButton rbselected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rbselected) + 1;

        checkSolution(answerNr ,rbselected);
    }

    private void checkSolution(int answerNr, RadioButton rbselected) {

        switch (currentQuestions.getAnswernr()){
            case 1 :
                if (currentQuestions.getAnswernr() == answerNr){
                    rb1.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_crt));
                     rb1.setTextColor(Color.WHITE);
                     correctans++;
                    Toast.makeText(Dashboard.this ,"Good Job!" ,Toast.LENGTH_SHORT).show();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);


                }else{
                    changtoIncorrectColor(rbselected);
                    wrongans++;
                    Toast.makeText(Dashboard.this ,"Ohff! try harder" ,Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);


                }
                break;

            case 2 :
                if (currentQuestions.getAnswernr() == answerNr){
                    rb2.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_crt));
                    rb2.setTextColor(Color.WHITE);
                    correctans++;
                    Toast.makeText(Dashboard.this ,"Good Job!" ,Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }else{
                    changtoIncorrectColor(rbselected);
                   wrongans++;
                    Toast.makeText(Dashboard.this ,"Ohff! try harder" ,Toast.LENGTH_SHORT).show();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);


                }
                break;
            case 3:
                if (currentQuestions.getAnswernr() == answerNr){
                    rb3.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_crt));
                    rb3.setTextColor(Color.WHITE);
                    correctans++;
                    Toast.makeText(Dashboard.this ,"Good Job!" ,Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }else{
                    changtoIncorrectColor(rbselected);
                   wrongans++;
                    Toast.makeText(Dashboard.this ,"Ohff! try harder" ,Toast.LENGTH_SHORT).show();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);


                }
                break;
            case 4 :
                if (currentQuestions.getAnswernr() == answerNr){
                    rb4.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_crt));
                    rb4.setTextColor(Color.WHITE);
                    correctans++;
                    Toast.makeText(Dashboard.this ,"Good Job!" ,Toast.LENGTH_SHORT).show();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }else{
                    changtoIncorrectColor(rbselected);
                  wrongans++;
                    Toast.makeText(Dashboard.this ,"Ohff! try harder" ,Toast.LENGTH_SHORT).show();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                break;
        }  // end of switch
        if(questioncounter < questiontotalcount){
            next.setText("confirm and finsh");
        }
    }

    private void changtoIncorrectColor(RadioButton rbselected) {
        rbselected.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_wr));
        rbselected.setTextColor(Color.WHITE);


    }

    private void showQuestions() {

        radioGroup.clearCheck();   // clears the history of selected
                   // setting backgroud of buttons
        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_background));

                                  // setting the text color
        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rb4.setTextColor(Color.BLACK);



        if(questioncounter < questiontotalcount){
            currentQuestions = questionslist.get(questioncounter);   // random question will be allocated
            tv_questions.setText(currentQuestions.getQuestion());
            rb1.setText(currentQuestions.getOption1());
            rb2.setText(currentQuestions.getOption2());
            rb3.setText(currentQuestions.getOption3());
            rb4.setText(currentQuestions.getOption4());
            questioncounter++;
            answered = false;
            next.setText("confirm");



           tv_questioncount.setText("Questions :"+questioncounter + "/" +questiontotalcount);
           tv_score.setText("Score :" + (correctans * 20 - wrongans *5) );


           timeleftinMills = COUNTDOWN_IN_MILLS;
           startCountDown();
    }
        else{
            totalSizeofQuiz = questionslist.size();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finalScoreDialog.finalScoreDialog(correctans,wrongans,totalSizeofQuiz);

                }
         }, 1000);
        }
    }


    private  void startCountDown(){
        countDownTimer  = new CountDownTimer(timeleftinMills, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftinMills = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
               timeleftinMills = 0;
               updateCountDownText();
            }
        }.start();
    }


    private  void  updateCountDownText(){
        int minutes = (int)(timeleftinMills / 1000 )/60;
        int seconds = (int)(timeleftinMills/1000) % 60;
        String  timeFOrmatted = String.format(Locale.getDefault(),"%02d:%02d" ,minutes,seconds);
        tv_countdown.setText(timeFOrmatted);
        
        if(timeleftinMills < 10000){
            tv_countdown.setTextColor(Color.RED);
        }else{
            tv_countdown.setTextColor(defaultTextColor);
        }

        if(timeleftinMills == 0){
            Toast.makeText(this, "Times UP:" , Toast.LENGTH_SHORT).show();



            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                    startActivity(intent);
                }
            } , 2000);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }

    }
}
