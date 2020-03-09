package com.example.features_andapplications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class brainTrainer extends AppCompatActivity {

    Button startButton,playAgainButton,button0,button1,button2,button3;
    ArrayList<Integer> sumArray= new ArrayList<>(3);
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score;
    TextView scoreTextView,sumTextView,timerTextView;
    int numberOfQuestions;

 

    //layout visibility / invisibility

    ConstraintLayout gameConstraintLayout;
    GridLayout gridLayout;

    public void start(View view){

        startButton.setVisibility(view.INVISIBLE);
        timerTextView.setAlpha(1);
        sumTextView.setAlpha(1);
        scoreTextView.setAlpha(1);
        gameConstraintLayout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    public void chooseAnswer(View view){

        //Toast.makeText(this, "Button Tapped:"+ view.getTag(), Toast.LENGTH_SHORT).show();

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreTextView.setText(score+"/"+numberOfQuestions);
        generateQuestion();
    }

    public void generateQuestion(){

        int a,b;
        Random random=new Random();
        a=random.nextInt(20);
        b=random.nextInt(20);
        sumTextView.setText(a+" + "+b);

        //generating sum and making appear at random postion
        locationOfCorrectAnswer=random.nextInt(3);

        //generating questions wheraeas there is no answer on the button ,solve this issue we have to clear sumArray whenever it generate another questions
        sumArray.clear();
        int incorrectAnswer;
        for (int i=0;i<4; i++){

            if(i==locationOfCorrectAnswer){
                sumArray.add(a+b);
            }
            else{
                //this will generate random number at non-sum postion ,but there is chance that answer and random matches where user
                //see 2 answers to avoid that situation we introduced int incorrectanswer
                incorrectAnswer=random.nextInt(40);

                while(incorrectAnswer==a+b){

                    incorrectAnswer=random.nextInt(40);

                }
                sumArray.add(incorrectAnswer);
            }
        }
        //setting Button to show random numbers and one button got answer



        button0.setText(Integer.toString(sumArray.get(0)));
        button1.setText(Integer.toString(sumArray.get(1)));
        button2.setText(Integer.toString(sumArray.get(2)));
        button3.setText(Integer.toString(sumArray.get(3)));

    }

    //for playing game again

    public void playAgain(View view){

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            child.setEnabled(true);
        }

        score=0;
        numberOfQuestions=0;
        scoreTextView.setText("0/0");
        timerTextView.setText("0s");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();


        final CountDownTimer countDownTimer=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(millisUntilFinished/1000+"s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score:"+score+"/"+numberOfQuestions);


                for (int i = 0; i < gridLayout.getChildCount(); i++) {
                    View child = gridLayout.getChildAt(i);
                    child.setEnabled(false);
                }

            }
        };
        countDownTimer.start();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_trainer);

        startButton=findViewById(R.id.startButton);
        //random sum generator
        sumTextView=findViewById(R.id.sumTextView);
        //updating result correct or wrond
        resultTextView=findViewById(R.id.resultTexView);
        //updating score bar
        scoreTextView=findViewById(R.id.scoreTextView);
        //timerText View
        timerTextView=findViewById(R.id.timerTextView);
        //play again the game button
        playAgainButton=findViewById(R.id.playAgainButton);

        //Layouts
        gameConstraintLayout=findViewById(R.id.gameConstraintLayout);

        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        gridLayout=findViewById(R.id.gridLayout);

    }
}
