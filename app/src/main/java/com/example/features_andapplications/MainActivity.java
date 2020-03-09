package com.example.features_andapplications;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button firstButton, secondButton, thirdButton, fourthButton, fifthButton,sixthButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ConstraintLayout constraintLayout = findViewById(R.id.constrainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

     /*   Thread thread=new Thread(){

            public void run(){

                try {
                    sleep(500);
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    Intent intent=new Intent(getApplicationContext(),javaScriptObjectNotation.class);
                    startActivity(intent);
                }

            }

        };thread.start();*/
       /* final TextView timerTextView=findViewById(R.id.timerTextView);

        new CountDownTimer(3000+100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {
                timerTextView.setAlpha(0);
                Intent intent=new Intent(getApplicationContext(),weatherForecaster.class);
                startActivity(intent);
            }
        }.start();*/


        firstButton = findViewById(R.id.firstButton);
        secondButton = findViewById(R.id.secondButton);
        thirdButton = findViewById(R.id.thirdButton);
        fourthButton = findViewById(R.id.fourthButton);
        fifthButton = findViewById(R.id.fifthButton);
        sixthButton=findViewById(R.id.sixthButton);

        firstButton.setText("Brain Trainer");
        secondButton.setText("Image Downloader");
        thirdButton.setText("webContent Downloader");
        fourthButton.setText("guessCelebrity");
        fifthButton.setText("weatherForecast");
        sixthButton.setText("eggTimer");

        firstButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        thirdButton.setOnClickListener(this);
        fourthButton.setOnClickListener(this);
        fifthButton.setOnClickListener(this);
        sixthButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()) {

            case R.id.firstButton:
                Toast.makeText(this, firstButton.getText().toString(), Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), brainTrainer.class);
                startActivity(intent);
                break;
            case R.id.secondButton:
                Toast.makeText(this, secondButton.getText().toString(), Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), downloadingImages.class);
                startActivity(intent);
                break;

            case R.id.thirdButton:
                Toast.makeText(this, thirdButton.getText().toString(), Toast.LENGTH_SHORT).show();

                intent = new Intent(getApplicationContext(), downloadingWebContent.class);
                startActivity(intent);
                break;

            case R.id.fourthButton:
                Toast.makeText(this, fourthButton.getText().toString(), Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), guessTheCelebrity.class);
                startActivity(intent);
                break;

            case R.id.fifthButton:
                Toast.makeText(this, fifthButton.getText().toString(), Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), weatherForecaster.class);
                startActivity(intent);
                break;

            case R.id.sixthButton:
                Toast.makeText(this, sixthButton.getText().toString(), Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), eggTimer.class);
                startActivity(intent);
                break;

        }
    }
}
