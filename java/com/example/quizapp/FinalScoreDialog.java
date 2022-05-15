package com.example.quizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class FinalScoreDialog extends AppCompatActivity {
   private Context mContext;
   private Dialog finalScoreDialog;
   private  TextView textviewfinalScore;
   private TextView name;


    public FinalScoreDialog(Context mContext) {
        this.mContext = mContext;
    }


    public  void finalScoreDialog(int correctAns , int wrongAns , int totalSizeofQuiz){

        finalScoreDialog = new Dialog(mContext);
        finalScoreDialog.setContentView(R.layout.final_score_dailog);
        final Button btFinalSCore = (Button)finalScoreDialog.findViewById(R.id.btn_final_score);
        textviewfinalScore = (TextView)finalScoreDialog.findViewById(R.id.text_final_score);
        finalScorecal(correctAns ,wrongAns,totalSizeofQuiz);
        btFinalSCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalScoreDialog.dismiss();
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }
        });
        finalScoreDialog.show();
        finalScoreDialog.setCancelable(false);
        finalScoreDialog.setCanceledOnTouchOutside(false);
    }

    public void finalScorecal(int correctans, int wrongans, int totalsizofQuiz) {
        int tempScore;
        if(correctans == totalsizofQuiz){
            tempScore = (correctans * 20) -(wrongans * 5);
            textviewfinalScore.setText("Final  score : \n      " + String.valueOf(tempScore));
        }
        else if(wrongans == totalsizofQuiz){
            tempScore =0;
            textviewfinalScore.setText("Final  score : \n      " + String.valueOf(tempScore));
        }else if( correctans > wrongans){
            tempScore = (correctans * 20) -(wrongans * 5);
            textviewfinalScore.setText("Final  score : \n       " + String.valueOf(tempScore));
        }else if( correctans < wrongans){
            tempScore = (correctans * 20) -(wrongans * 5);
            textviewfinalScore.setText("Final  score : \n        " + String.valueOf(tempScore));
        }else{
            tempScore = (correctans * 20) -(wrongans * 5);
            textviewfinalScore.setText("Final  score : \n        " + String.valueOf(tempScore));
        }

    }
}
