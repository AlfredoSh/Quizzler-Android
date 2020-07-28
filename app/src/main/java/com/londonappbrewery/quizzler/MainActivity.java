package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here

    // TODO: Declare member variables here:
    Button mTruebutton, mFalsebutton;
    TextView mQuestionTextView, mScoreTextView;
    int mIndex;
    int mQuestion;
    int mScore;
    ProgressBar mProgressBar;


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, false),
            new TrueFalse(R.string.question_3, false),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, true),
            new TrueFalse(R.string.question_7, false),
            new TrueFalse(R.string.question_8, true),
            new TrueFalse(R.string.question_9, false),
            new TrueFalse(R.string.question_10, true)
    };
    final int progress_bar_increment= (int) Math.ceil(100.00/mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mTruebutton = (Button) findViewById(R.id.true_button);
         mFalsebutton = (Button) findViewById(R.id.false_button);
         mQuestionTextView = (TextView)  findViewById(R.id.question_text_view);

         mScoreTextView = (TextView) findViewById(R.id.score);
         mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

         //mQuestionTextView.setText("hello world");

        mQuestion =  mQuestionBank[mIndex].getmQuestionID();
        mQuestionTextView.setText(mQuestion);

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                updateQuestion();
            }
        };

        mTruebutton.setOnClickListener(myListener);
        mFalsebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuestion();
            }
        });

    }

    private void updateQuestion(){
        mIndex= (mIndex + 1) % mQuestionBank.length;
        // Present an alert dialog if we reach the end.

        if (mIndex==0){
          //  Toast.makeText(this,"Funciona",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Jogo Finalizado");
            alert.setCancelable(false);
            alert.setMessage("Sua pontuação " + mScore + " pontos!");
            alert.setPositiveButton("Fechar aplicativo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }
        mQuestion= mQuestionBank[mIndex].getmQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(progress_bar_increment);
        mScoreTextView.setText("Pontos: " + mScore + "/" + mQuestionBank.length);

    }

    private void checkAnswer(boolean userSelection){
        boolean correctAnswer = mQuestionBank[mIndex].ismAnswer();

        if (userSelection==correctAnswer){
          //  Toast.makeText(getApplicationContext(),"Correto",Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
          //  Toast.makeText(getApplicationContext(),"Sorry",Toast.LENGTH_SHORT).show();

        }
    }
}
