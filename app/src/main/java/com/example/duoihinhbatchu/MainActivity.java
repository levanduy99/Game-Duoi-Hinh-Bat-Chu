package com.example.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duoihinhbatchu.API.GetQuestion;

public class MainActivity extends AppCompatActivity {

    //private Button btnPlay;
    private Button btnIntroduce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        //get data
        new GetQuestion ().execute();
        //

//        btnPlay = (Button) findViewById (R.id.btn_play);

//        btnPlay.setOnClickListener (new View.OnClickListener ( ) {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent (MainActivity.this,PlayGameActivity.class);
//                startActivity (intent);
//            }
//        });

        btnIntroduce = (Button) findViewById (R.id.btn_introduction);
        btnIntroduce.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        IntroductionDialog introductionDialog = new IntroductionDialog ();
        introductionDialog.show (getSupportFragmentManager (),"");
    }

    public void playGame(View view) {
        if(Data.getData ().arrRiddle.size ()>0){
            startActivity (new Intent (this,PlayGameActivity.class));
        }
    }
}